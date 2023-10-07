package com.mitsko.financial_system.repository.impl;

import com.mitsko.financial_system.domain.entity.ExchangeRate;
import com.mitsko.financial_system.domain.enums.Currency;
import com.mitsko.financial_system.exception.ConnectionPoolException;
import com.mitsko.financial_system.repository.ExchangeRateRepository;
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

import static com.mitsko.financial_system.constant.repository.ExchangeRateRepositoryConstants.*;

public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {

    private final Logger logger = LoggerFactory.getLogger(ExchangeRateRepositoryImpl.class);

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Optional<ExchangeRate> getByCurrencies(Currency first, Currency second) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_RATE_BY_CURRENCIES);

            preparedStatement.setString(1, first.name());
            preparedStatement.setString(2, second.name());
            preparedStatement.setString(3, second.name());
            preparedStatement.setString(4, first.name());

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
    public void updateByUuid(BigDecimal newRate, String uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_RATE_BY_UUID);

            preparedStatement.setBigDecimal(1, newRate);
            preparedStatement.setString(2, uuid);

            preparedStatement.executeUpdate();

            logger.info("Set new rate: {}, for {}", newRate, uuid);
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

            logger.info("Delete rate with uuid: {}", uuid);
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public List<ExchangeRate> list() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        LinkedList<ExchangeRate> list = new LinkedList<>();

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_ALL_RATES);

            resultSet = preparedStatement.executeQuery();

            compileList(resultSet, list);
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
        return list;
    }

    private ExchangeRate toEntity(ResultSet resultSet) throws SQLException {
        String uuid = resultSet.getString(1);
        Currency firstCurrency = Currency.valueOf(resultSet.getString(2));
        Currency secondCurrency = Currency.valueOf(resultSet.getString(3));
        BigDecimal rate = resultSet.getBigDecimal(4);

        return new ExchangeRate(uuid, firstCurrency, secondCurrency, rate);
    }

    private void compileList(ResultSet resultSet, LinkedList<ExchangeRate> list) throws SQLException {
        while (resultSet.next()) {
            list.add(toEntity(resultSet));
        }
    }
}
