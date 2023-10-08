package com.mitsko.financialsystem.domain.dto;

import com.mitsko.financialsystem.domain.dto.base.BaseDto;
import com.mitsko.financialsystem.domain.enums.Currency;

import java.math.BigDecimal;

public class AccountResponseDto extends BaseDto {

    private BigDecimal balance;

    private BankDto bank;

    private Currency currency;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BankDto getBank() {
        return bank;
    }

    public void setBank(BankDto bank) {
        this.bank = bank;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
