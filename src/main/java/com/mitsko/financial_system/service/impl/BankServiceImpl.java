package com.mitsko.financial_system.service.impl;

import com.mitsko.financial_system.domain.dto.BankDto;
import com.mitsko.financial_system.domain.entity.Bank;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.repository.BankRepository;
import com.mitsko.financial_system.repository.RepositoryFactory;
import com.mitsko.financial_system.service.BankService;
import com.mitsko.financial_system.service.util.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    public BankServiceImpl() {
        this.bankRepository = RepositoryFactory.getInstance().getBankRepository();
    }

    @Override
    public void add(BankDto createDto) throws ValidationException {
        if (!Validator.validatePercent(createDto.getIndividualsCommission())
                && Validator.validatePercent(createDto.getLegalEntitiesCommission())) {

            throw new ValidationException("Commission value must be between 0 and 100");
        }

        Bank bank = new Bank(createDto.getName(), createDto.getIndividualsCommission(),
                createDto.getLegalEntitiesCommission());

        bankRepository.save(bank);
    }

    @Override
    public List<BankDto> getAll() {
        return bankRepository.list().stream().map(this::toDto).collect(Collectors.toList());
    }

    private BankDto toDto(Bank bank) {
        BankDto dto = new BankDto();
        dto.setName(bank.getName());
        dto.setIndividualsCommission(bank.getIndividualsCommission());
        dto.setLegalEntitiesCommission(bank.getLegalEntitiesCommission());

        return dto;
    }
}
