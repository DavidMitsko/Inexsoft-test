package com.mitsko.financial_system.repository.impl;

import com.mitsko.financial_system.domain.entity.Client;
import com.mitsko.financial_system.domain.enums.ClientType;
import com.mitsko.financial_system.exception.ConnectionPoolException;
import com.mitsko.financial_system.repository.ClientRepository;
import com.mitsko.financial_system.repository.connection_pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.mitsko.financial_system.constant.repository.ClientRepositoryConstants.*;

public class ClientRepositoryImpl implements ClientRepository {

    private final Logger logger = LoggerFactory.getLogger(ClientRepositoryImpl.class);

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void save(Client client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CREATE_NEW_CLIENT);

            preparedStatement.setString(1, client.getUuid());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getSurname());
            preparedStatement.setInt(4, client.getAge());
            preparedStatement.setString(5, client.getClientType().name());

            preparedStatement.executeUpdate();

            logger.info("Created {} {} client", client.getName(), client.getSurname());
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public Optional<Client> getByUuid(String uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(GET_CLIENT_BY_UUID);

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
    public void updateByUuid(Client client, String uuid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_CLIENT_BY_UUID);

            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setInt(3, client.getAge());
            preparedStatement.setString(4, client.getClientType().name());

            preparedStatement.executeUpdate();

            logger.info("Updated {} {} client", client.getName(), client.getSurname());
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

            logger.info("Delete client with uuid: {}", uuid);
        } catch (SQLException | ConnectionPoolException ex) {
            logger.error(ex.getMessage());
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    private Client toEntity(ResultSet resultSet) throws SQLException {
        String uuid = resultSet.getString(1);
        String name = resultSet.getString(2);
        String surname = resultSet.getString(3);
        int age = resultSet.getInt(4);
        ClientType clientType = ClientType.valueOf(resultSet.getString(5));

        return new Client(uuid, name, surname, age, clientType);
    }

}
