package com.mitsko.financialsystem.domain.dto;

import com.mitsko.financialsystem.domain.enums.Currency;

import java.math.BigDecimal;

public class AccountDto {

    private BigDecimal balance;

    private String clientUuid;

    private String bankUuid;

    private Currency currency;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getClientUuid() {
        return clientUuid;
    }

    public void setClientUuid(String clientUuid) {
        this.clientUuid = clientUuid;
    }

    public String getBankUuid() {
        return bankUuid;
    }

    public void setBankUuid(String bankUuid) {
        this.bankUuid = bankUuid;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
