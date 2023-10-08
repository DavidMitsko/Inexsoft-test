package com.mitsko.financial_system.service.impl;

import com.mitsko.financial_system.domain.dto.ExchangeRateDto;
import com.mitsko.financial_system.domain.entity.ExchangeRate;
import com.mitsko.financial_system.repository.ExchangeRateRepository;
import com.mitsko.financial_system.repository.RepositoryFactory;
import com.mitsko.financial_system.service.ExchangeRateService;

import java.util.List;
import java.util.stream.Collectors;

public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateServiceImpl() {
        this.exchangeRateRepository = RepositoryFactory.getInstance().exchangeRateRepository();
    }

    @Override
    public List<ExchangeRateDto> list() {
        return exchangeRateRepository.list().stream().map(this::toDto).collect(Collectors.toList());
    }

    private ExchangeRateDto toDto(ExchangeRate exchangeRate) {
        ExchangeRateDto dto = new ExchangeRateDto();

        dto.setFirstCurrency(exchangeRate.getFirstCurrency());
        dto.setSecondCurrency(exchangeRate.getSecondCurrency());
        dto.setRate(exchangeRate.getRate());

        return dto;
    }
}
