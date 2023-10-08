package com.mitsko.financialsystem.service.impl;

import com.mitsko.financialsystem.domain.dto.BankDto;
import com.mitsko.financialsystem.domain.entity.Account;
import com.mitsko.financialsystem.domain.entity.Bank;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.repository.AccountRepository;
import com.mitsko.financialsystem.repository.BankRepository;
import com.mitsko.financialsystem.repository.RepositoryFactory;
import com.mitsko.financialsystem.repository.TransactionRepository;
import com.mitsko.financialsystem.service.BankService;
import com.mitsko.financialsystem.service.util.Validator;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankServiceImpl() {
        this.bankRepository = RepositoryFactory.getInstance().getBankRepository();
        this.accountRepository = RepositoryFactory.getInstance().accountRepository();
        this.transactionRepository = RepositoryFactory.getInstance().transactionRepository();
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
        return bankRepository.getAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteByUuid(String uuid) {
        if (!Validator.validateUuid(uuid)) {
            throw new ValidationException("Invalid uuid");
        }

        List<Account> accounts = accountRepository.getAllByBankUuid(uuid);

        String transactionId = UUID.randomUUID().toString();
        accounts.forEach(account -> {
            transactionRepository.deleteByAccountUuid(account.getUuid(),
                    true, transactionId, false);
            accountRepository.deleteByUuid(account.getUuid(), true, transactionId, false);
        });

        bankRepository.deleteByUuid(uuid, true, transactionId, true);
    }

    private BankDto toDto(Bank bank) {
        BankDto dto = new BankDto();
        dto.setUuid(bank.getUuid());
        dto.setName(bank.getName());
        dto.setIndividualsCommission(bank.getIndividualsCommission());
        dto.setLegalEntitiesCommission(bank.getLegalEntitiesCommission());

        return dto;
    }
}
