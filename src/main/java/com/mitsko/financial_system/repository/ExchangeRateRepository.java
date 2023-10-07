package com.mitsko.financial_system.repository;

import com.mitsko.financial_system.domain.entity.ExchangeRate;
import com.mitsko.financial_system.domain.enums.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository {

    Optional<ExchangeRate> getByCurrencies(Currency first, Currency second);

    void updateByUuid(BigDecimal newRate, String uuid);

    void deleteByUuid(String uuid);

    List<ExchangeRate> list();

}
