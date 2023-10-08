package com.mitsko.financialsystem.service.impl;

import com.mitsko.financialsystem.domain.dto.ExchangeRateDto;
import com.mitsko.financialsystem.domain.entity.ExchangeRate;
import com.mitsko.financialsystem.repository.ExchangeRateRepository;
import com.mitsko.financialsystem.repository.RepositoryFactory;
import com.mitsko.financialsystem.service.ExchangeRateService;

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
