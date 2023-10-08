package com.mitsko.financialsystem.domain.entity;

import com.mitsko.financialsystem.domain.entity.base.BaseEntity;
import com.mitsko.financialsystem.domain.enums.Currency;

import java.math.BigDecimal;
import java.util.Objects;

public class Account extends BaseEntity {

    private BigDecimal balance;

    private String clientUuid;

    private String bankUuid;

    private final Currency currency;

    public Account(String uuid, BigDecimal balance, String clientUuid, String bankUuid, Currency currency) {
        super(uuid);
        this.balance = balance;
        this.clientUuid = clientUuid;
        this.bankUuid = bankUuid;
        this.currency = currency;
    }

    public Account(BigDecimal balance, String clientUuid, String bankUuid, Currency currency) {
        this.balance = balance;
        this.clientUuid = clientUuid;
        this.bankUuid = bankUuid;
        this.currency = currency;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return balance.equals(account.balance) && clientUuid.equals(account.clientUuid) && bankUuid.equals(account.bankUuid) && currency.equals(account.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, clientUuid, bankUuid, currency);
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", clientUuid='" + clientUuid + '\'' +
                ", bankUuid='" + bankUuid + '\'' +
                ", currencyUuid='" + currency + '\'' +
                '}';
    }
}
