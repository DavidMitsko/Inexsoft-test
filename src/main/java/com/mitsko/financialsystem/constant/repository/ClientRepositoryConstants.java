package com.mitsko.financialsystem.constant.repository;

public class ClientRepositoryConstants {

    public static final String CREATE_NEW_CLIENT = "INSERT INTO client(uuid, name, surname, age, client_type)" +
            " VALUES(?,?,?,?, ?)";
    public static final String GET_CLIENT_BY_UUID = "SELECT * FROM client WHERE uuid = ?";
    public static final String UPDATE_CLIENT_BY_UUID = "UPDATE client SET name = ?, surname = ?, " +
            "age = ?, client_type = ? WHERE uuid = ?";
    public static final String DELETE_BY_UUID = "DELETE FROM client WHERE uuid = ?";

}
