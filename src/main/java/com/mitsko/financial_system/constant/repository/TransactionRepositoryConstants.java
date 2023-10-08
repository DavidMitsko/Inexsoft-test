package com.mitsko.financial_system.constant.repository;

public class TransactionRepositoryConstants {

    public static final String CREATE_NEW_TRANSACTION = "INSERT INTO transaction(uuid, transaction_time, sender_account_uuid, " +
            " recipient_account_uuid, amount, commission) VALUES(?,?,?,?,?,?)";
    public static final String GET_TRANSACTION_BY_UUID = "SELECT * FROM transaction WHERE uuid = ?";
    public static final String GET_ALL_TRANSACTIONS_BY_SENDER_AND_TIME_BETWEEN = "SELECT * FROM transaction WHERE " +
            "sender_account_uuid = ? AND transaction_time > ? AND transaction_time < ?";
    public static final String GET_ALL_TRANSACTIONS_BY_SENDER = "SELECT * FROM transaction WHERE " +
            "sender_account_uuid = ?";
    public static final String DELETE_BY_ACCOUNT = "DELETE FROM transaction WHERE sender_account_uuid = ? " +
            "OR recipient_account_uuid = ?";

}
