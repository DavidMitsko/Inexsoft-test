package com.mitsko.financial_system.constant.repository;

public class BankRepositoryConstants {

    public static final String CREATE_NEW_BANK = "INSERT INTO bank(uuid, name, individuals_commission, legal_entities_commission)" +
            " VALUES(?,?,?,?)";
    public static final String GET_BANK_BY_UUID = "SELECT uuid, name, individuals_commission, legal_entities_commission " +
            "FROM bank WHERE uuid = ?";
    public static final String GET_ALL_BANKS = "SELECT * FROM bank";
    public static final String UPDATE_BANK_BY_UUID = "UPDATE bank SET name = ?, individuals_commission = ?, " +
            "legal_entities_commission = ? WHERE uuid = ?";
    public static final String DELETE_BY_UUID = "DELETE FROM bank WHERE uuid = ?";

}
