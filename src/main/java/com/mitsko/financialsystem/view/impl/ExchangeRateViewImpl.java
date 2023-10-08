package com.mitsko.financialsystem.view.impl;

import com.mitsko.financialsystem.domain.dto.ExchangeRateDto;
import com.mitsko.financialsystem.view.View;

import java.util.List;

public class ExchangeRateViewImpl implements View<List<ExchangeRateDto>> {

    @Override
    public void printResult(List<ExchangeRateDto> exchangeRateDtos) {
        System.out.println("Rates: ");

        exchangeRateDtos.forEach(rate -> System.out.printf("First currency: %s. Second currency: %s. Rate: %s%n",
                rate.getFirstCurrency(), rate.getSecondCurrency(), rate.getRate()));
    }
}
