package com.mitsko.financial_system.domain.entity;

import com.mitsko.financial_system.domain.entity.base.BaseEntity;
import com.mitsko.financial_system.domain.enums.Currency;

import java.math.BigDecimal;
import java.util.Objects;

public class ExchangeRate extends BaseEntity {

    private Currency firstCurrency;

    private Currency secondCurrency;

    private BigDecimal rate;

    public ExchangeRate(Currency firstCurrency, Currency secondCurrency, BigDecimal rate) {
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
        this.rate = rate;
    }

    public ExchangeRate(String uuid, Currency firstCurrency, Currency secondCurrency, BigDecimal rate) {
        super(uuid);
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
        this.rate = rate;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate that = (ExchangeRate) o;
        return firstCurrency == that.firstCurrency && secondCurrency == that.secondCurrency && rate.equals(that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstCurrency, secondCurrency, rate);
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "firstCurrency=" + firstCurrency +
                ", secondCurrency=" + secondCurrency +
                ", rate=" + rate +
                '}';
    }
}
