package com.mitsko.financial_system.constant.repository;

public class AccountRepositoryConstants {

    public static final String CREATE_NEW_ACCOUNT = "INSERT INTO account(uuid, balance, client_uuid, bank_uuid, currency)" +
            " VALUES(?,?,?,?,?)";
    public static final String GET_ACCOUNT_BY_UUID = "SELECT * FROM account WHERE uuid = ?";
    public static final String GET_ALL_ACCOUNTS_BY_CLIENT = "SELECT * FROM account WHERE client_uuid = ?";
    public static final String UPDATE_ACCOUNT_BALANCE_BY_CLIENT = "UPDATE account SET balance = ? WHERE client_uuid = ?";
    public static final String DELETE_BY_UUID = "DELETE account WHERE uuid = ?";

}
