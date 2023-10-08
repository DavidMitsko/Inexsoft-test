package com.mitsko.financial_system.repository.impl;

import com.mitsko.financial_system.domain.entity.Account;
import com.mitsko.financial_system.domain.enums.Currency;
import com.mitsko.financial_system.exception.ConnectionPoolException;
import com.mitsko.financial_system.repository.AccountRepository;
import com.mitsko.financial_system.repository.connection_pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.mitsko.financial_system.constant.repository.AccountRepositoryConstants.*;

public class AccountRepositoryImpl implements AccountRepository {

    private final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void save(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CREATE_NEW_ACCOUNT);

            preparedStatement.setString(1, account.getUuid());
            preparedStatement.setBigDecimal(2, account.getBalance());
            preparedStatement.setString(3, account.getClientUuid());
            preparedStatement.setString(4, account.getBankUuid());
            preparedStatement.setString(5, account.getCurrency().name());

            preparedStatement.executeUpdate();

            logger.info("Created {} client account", account.getClientUuid());
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public Optional<Account> getByUuid(String uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_UUID);

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
    public void updateByUuid(Account account, String uuid, boolean transactional, String transactionId, boolean lastAction) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            if (transactional) {
                connection = connectionPool.takeTransactionalConnection(transactionId);
                connection.setAutoCommit(false);
            } else {
                connection = connectionPool.takeConnection();
            }

            preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_BALANCE_BY_CLIENT);

            preparedStatement.setBigDecimal(1, account.getBalance());
            preparedStatement.setString(2, account.getClientUuid());

            preparedStatement.executeUpdate();

            logger.info("Updated {} client balance in {} account", account.getClientUuid(), account.getUuid());
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
                    if (lastAction && connection.getAutoCommit()) {
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
    public void deleteByUuid(String uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_BY_UUID);

            preparedStatement.setString(1, uuid);
            preparedStatement.executeUpdate();

            logger.info("Delete account with uuid: {}", uuid);
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public List<Account> getAllByClientUuid(String clientUuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LinkedList<Account> list = new LinkedList<>();

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_ACCOUNTS_BY_CLIENT);

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

    private Account toEntity(ResultSet resultSet) throws SQLException {
        String uuid = resultSet.getString(1);
        BigDecimal balance = resultSet.getBigDecimal(2);
        String clientUuid = resultSet.getString(3);
        String bankUuid = resultSet.getString(4);
        Currency currency = Currency.valueOf(resultSet.getString(5));

        return new Account(uuid, balance, clientUuid, bankUuid, currency);
    }

    private void compileList(ResultSet resultSet, LinkedList<Account> list) throws SQLException {
        while (resultSet.next()) {
            list.add(toEntity(resultSet));
        }
    }
}
