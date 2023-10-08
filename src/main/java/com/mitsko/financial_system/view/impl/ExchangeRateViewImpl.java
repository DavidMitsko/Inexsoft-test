package com.mitsko.financial_system.view.impl;

import com.mitsko.financial_system.domain.dto.ExchangeRateDto;
import com.mitsko.financial_system.view.View;

import java.util.List;

public class ExchangeRateViewImpl implements View<List<ExchangeRateDto>> {

    @Override
    public void printResult(List<ExchangeRateDto> exchangeRateDtos) {
        System.out.println("Rates: ");

        exchangeRateDtos.forEach(rate -> System.out.printf("First currency: %s. Second currency: %s. Rate: %s%n",
                rate.getFirstCurrency(), rate.getSecondCurrency(), rate.getRate()));
    }
}
