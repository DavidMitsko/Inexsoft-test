package com.mitsko.financial_system.domain.dto;

import com.mitsko.financial_system.domain.enums.Currency;

import java.math.BigDecimal;

public class ExchangeRateDto {

    private Currency firstCurrency;

    private Currency secondCurrency;

    private BigDecimal rate;

    public Currency getFirstCurrency() {
        return firstCurrency;
    }

    public void setFirstCurrency(Currency firstCurrency) {
        this.firstCurrency = firstCurrency;
    }

    public Currency getSecondCurrency() {
        return secondCurrency;
    }

    public void setSecondCurrency(Currency secondCurrency) {
        this.secondCurrency = secondCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
