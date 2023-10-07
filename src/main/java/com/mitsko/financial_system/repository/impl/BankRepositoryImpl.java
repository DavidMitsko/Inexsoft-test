package com.mitsko.financial_system.repository.impl;

import com.mitsko.financial_system.domain.entity.Bank;
import com.mitsko.financial_system.exception.ConnectionPoolException;
import com.mitsko.financial_system.repository.BankRepository;
import com.mitsko.financial_system.repository.connection_pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.mitsko.financial_system.constant.repository.BankRepositoryConstants.*;

public class BankRepositoryImpl implements BankRepository {

    private final Logger logger = LoggerFactory.getLogger(BankRepositoryImpl.class);

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void save(Bank bank) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CREATE_NEW_BANK);

            preparedStatement.setString(1, bank.getUuid());
            preparedStatement.setString(2, bank.getName());
            preparedStatement.setInt(3, bank.getIndividualsCommission());
            preparedStatement.setInt(4, bank.getLegalEntitiesCommission());

            preparedStatement.executeUpdate();

            logger.info("Created {} bank", bank.getName());
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public Optional<Bank> getByUuid(String uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_BANK_BY_UUID);

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
    public List<Bank> list() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LinkedList<Bank> list = new LinkedList<>();

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_BANKS);

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
    public void updateByUuid(Bank bank, String uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_BANK_BY_UUID);

            preparedStatement.setString(1, bank.getName());
            preparedStatement.setInt(2, bank.getIndividualsCommission());
            preparedStatement.setInt(3, bank.getLegalEntitiesCommission());
            preparedStatement.setString(4, bank.getUuid());

            preparedStatement.executeUpdate();

            logger.info("Updated {} bank", bank.getName());
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
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

            logger.info("Delete bank with uuid: {}", uuid);
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    private Bank toEntity(ResultSet resultSet) throws SQLException {
        String uuid = resultSet.getString(1);
        String name = resultSet.getString(2);
        int individualsCommission = resultSet.getInt(3);
        int legalEntitiesCommission = resultSet.getInt(4);

        return new Bank(uuid, name, individualsCommission, legalEntitiesCommission);
    }

    private void compileList(ResultSet resultSet, LinkedList<Bank> list) throws SQLException {
        while (resultSet.next()) {
            list.add(toEntity(resultSet));
        }
    }

}
