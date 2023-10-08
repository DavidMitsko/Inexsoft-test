package com.mitsko.financialsystem.repository;

import com.mitsko.financialsystem.domain.entity.ExchangeRate;
import com.mitsko.financialsystem.domain.enums.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository {

    Optional<ExchangeRate> getByCurrencies(Currency first, Currency second);

    void updateByUuid(BigDecimal newRate, String uuid);

    void deleteByUuid(String uuid);

    List<ExchangeRate> list();

}
