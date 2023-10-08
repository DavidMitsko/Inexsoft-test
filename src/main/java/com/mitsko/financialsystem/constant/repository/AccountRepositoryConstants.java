package com.mitsko.financialsystem.constant.repository;

public class AccountRepositoryConstants {

    public static final String CREATE_NEW_ACCOUNT = "INSERT INTO account(uuid, balance, client_uuid, bank_uuid, currency)" +
            " VALUES(?,?,?,?,?)";
    public static final String GET_ACCOUNT_BY_UUID = "SELECT uuid, balance, client_uuid, bank_uuid, currency " +
            "FROM account WHERE uuid = ?";
    public static final String GET_ALL_ACCOUNTS_BY_CLIENT = "SELECT uuid, balance, client_uuid, bank_uuid, currency " +
            "FROM account WHERE client_uuid = ?";
    public static final String UPDATE_ACCOUNT_BALANCE_BY_CLIENT = "UPDATE account SET balance = ? WHERE client_uuid = ?";
    public static final String DELETE_BY_UUID = "DELETE FROM account WHERE uuid = ?";
    public static final String GET_ALL_BY_BANK = "SELECT uuid, balance, client_uuid, bank_uuid, currency " +
            "FROM account WHERE bank_uuid = ?";
    public static final String DELETE_ALL_BY_BANK = "DELETE FROM account WHERE bank_uuid = ?";

}
