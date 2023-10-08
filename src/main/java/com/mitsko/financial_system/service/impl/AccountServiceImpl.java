package com.mitsko.financial_system.service.impl;

import com.mitsko.financial_system.domain.dto.AccountDto;
import com.mitsko.financial_system.domain.dto.AccountResponseDto;
import com.mitsko.financial_system.domain.dto.BankDto;
import com.mitsko.financial_system.domain.entity.Account;
import com.mitsko.financial_system.domain.entity.Bank;
import com.mitsko.financial_system.exception.EntityNotFoundException;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.repository.AccountRepository;
import com.mitsko.financial_system.repository.BankRepository;
import com.mitsko.financial_system.repository.RepositoryFactory;
import com.mitsko.financial_system.service.AccountService;
import com.mitsko.financial_system.service.util.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;

    public AccountServiceImpl() {
        this.accountRepository = RepositoryFactory.getInstance().accountRepository();
        this.bankRepository = RepositoryFactory.getInstance().getBankRepository();
    }

    @Override
    public void add(AccountDto dto) throws ValidationException {
        if (Validator.validateAmount(dto.getBalance())) {
            throw new ValidationException("Balance can not be negative");
        }

        Account account = new Account(dto.getBalance(), dto.getClientUuid(), dto.getBankUuid(), dto.getCurrency());
        accountRepository.save(account);
    }

    @Override
    public List<AccountResponseDto> getAccountsByClientUuid(String clientUuid) {
        if (!Validator.validateUuid(clientUuid)) {
            throw new ValidationException("Invalid uuid");
        }
        List<Account> accounts = accountRepository.getAllByClientUuid(clientUuid);
        return accounts.stream().map(account -> {
            Bank bank = bankRepository.getByUuid(account.getBankUuid())
                    .orElseThrow(() -> new EntityNotFoundException(String
                            .format("Class bank can not be found with param %s", account.getBankUuid())));

            return toDto(account, bank);
        }).collect(Collectors.toList());
    }

    private AccountResponseDto toDto(Account account, Bank bank) {
        AccountResponseDto responseDto = new AccountResponseDto();

        responseDto.setUuid(account.getUuid());
        responseDto.setBalance(account.getBalance());
        responseDto.setCurrency(account.getCurrency());

        BankDto bankDto = new BankDto();
        bankDto.setName(bank.getName());
        bankDto.setIndividualsCommission(bank.getIndividualsCommission());
        bankDto.setLegalEntitiesCommission(bank.getLegalEntitiesCommission());

        responseDto.setBank(bankDto);

        return responseDto;
    }
}
