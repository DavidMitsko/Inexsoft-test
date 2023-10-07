package com.mitsko.financial_system.constant.repository;

public class ExchangeRateRepositoryConstants {

    public static final String GET_RATE_BY_CURRENCIES = "SELECT * FROM exchange_rate WHERE " +
            "(first_currency = ? AND second_currency = ?) OR (first_currency = ? AND second_currency = ?)";
    public static final String GET_ALL_RATES = "SELECT * FROM exchange_rate";
    public static final String UPDATE_RATE_BY_UUID = "UPDATE exchange_rate SET rate = ? " +
            "WHERE uuid = ?";
    public static final String DELETE_BY_UUID = "DELETE exchange_rate WHERE uuid = ?";

}
