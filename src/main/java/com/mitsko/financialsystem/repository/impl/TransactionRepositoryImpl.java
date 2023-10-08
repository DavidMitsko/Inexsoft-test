package com.mitsko.financialsystem.repository.impl;

import com.mitsko.financialsystem.domain.entity.Transaction;
import com.mitsko.financialsystem.exception.ConnectionPoolException;
import com.mitsko.financialsystem.repository.TransactionRepository;
import com.mitsko.financialsystem.repository.connectionpool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.mitsko.financialsystem.constant.repository.TransactionRepositoryConstants.*;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final Logger logger = LoggerFactory.getLogger(TransactionRepositoryImpl.class);

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void save(Transaction transaction, boolean transactional, String transactionId, boolean lastAction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            if (transactional) {
                connection = connectionPool.takeTransactionalConnection(transactionId);
                connection.setAutoCommit(false);
            } else {
                connection = connectionPool.takeConnection();
            }
            preparedStatement = connection.prepareStatement(CREATE_NEW_TRANSACTION);

            preparedStatement.setString(1, transaction.getUuid());
            preparedStatement.setObject(2, Timestamp.valueOf(transaction.getTransactionTime()));
            preparedStatement.setString(3, transaction.getSenderAccountUuid());
            preparedStatement.setString(4, transaction.getRecipientAccountUuid());
            preparedStatement.setBigDecimal(5, transaction.getAmount());
            preparedStatement.setBigDecimal(6, transaction.getCommission());

            preparedStatement.executeUpdate();

            logger.info("Created transaction between {} and {}", transaction.getSenderAccountUuid(),
                    transaction.getRecipientAccountUuid());
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
            if (transactional) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    connectionPool.closeTransactionalConnection(transactionId);
                    connectionPool.closeConnection(connection, preparedStatement);
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
        } finally {
            if (!transactional) {
                connectionPool.closeConnection(connection, preparedStatement);
            } else {
                try {
                    if (lastAction && !connection.getAutoCommit()) {
                        connection.commit();
                        connection.setAutoCommit(true);
                        connectionPool.closeTransactionalConnection(transactionId);
                        connectionPool.closeConnection(connection, preparedStatement);
                    } else {
                        connectionPool.addConnectionToTransaction(connection, transactionId);
                        preparedStatement.close();
                    }
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    @Override
    public void deleteByAccountUuid(String accountUuid, boolean transactional, String transactionId, boolean lastAction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            if (transactional) {
                connection = connectionPool.takeTransactionalConnection(transactionId);
                connection.setAutoCommit(false);
            } else {
                connection = connectionPool.takeConnection();
            }
            preparedStatement = connection.prepareStatement(DELETE_BY_ACCOUNT);

            preparedStatement.setString(1, accountUuid);
            preparedStatement.setString(2, accountUuid);

            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
            if (transactional) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    connectionPool.closeTransactionalConnection(transactionId);
                    connectionPool.closeConnection(connection, preparedStatement);
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
        } finally {
            if (!transactional) {
                connectionPool.closeConnection(connection, preparedStatement);
            } else {
                try {
                    if (lastAction && !connection.getAutoCommit()) {
                        connection.commit();
                        connection.setAutoCommit(true);
                        connectionPool.closeTransactionalConnection(transactionId);
                        connectionPool.closeConnection(connection, preparedStatement);
                    } else {
                        connectionPool.addConnectionToTransaction(connection, transactionId);
                        preparedStatement.close();
                    }
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    @Override
    public Optional<Transaction> getByUuid(String uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_TRANSACTION_BY_UUID);

            preparedStatement.setString(1, uuid);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(toEntity(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return Optional.empty();
    }

    @Override
    public List<Transaction> getBySenderUuidAndTransactionTimeBetween(String clientUuid, LocalDateTime startTime, LocalDateTime endTime) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LinkedList<Transaction> list = new LinkedList<>();

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_TRANSACTIONS_BY_SENDER_AND_TIME_BETWEEN);

            preparedStatement.setString(1, clientUuid);
            preparedStatement.setObject(2, startTime);
            preparedStatement.setObject(3, endTime);

            resultSet = preparedStatement.executeQuery();

            compileList(resultSet, list);
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public List<Transaction> getBySenderUuid(String clientUuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LinkedList<Transaction> list = new LinkedList<>();

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_TRANSACTIONS_BY_SENDER);

            preparedStatement.setString(1, clientUuid);

            resultSet = preparedStatement.executeQuery();

            compileList(resultSet, list);
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return list;
    }

    private Transaction toEntity(ResultSet resultSet) throws SQLException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Transaction.builder()
                .setUuid(resultSet.getString(1))
                .setTransactionTime(LocalDateTime.parse(resultSet.getString(2).substring(0, 19), dateTimeFormatter))
                .setSenderAccountUuid(resultSet.getString(3))
                .setRecipientAccountUuid(resultSet.getString(4))
                .setAmount(resultSet.getBigDecimal(5))
                .setCommission(resultSet.getBigDecimal(6))
                .build();
    }

    private void compileList(ResultSet resultSet, LinkedList<Transaction> list) throws SQLException {
        while (resultSet.next()) {
            list.add(toEntity(resultSet));
        }
    }
}
