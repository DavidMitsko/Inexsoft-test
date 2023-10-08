package com.mitsko.financialsystem.service.impl;

import com.mitsko.financialsystem.domain.dto.AccountDto;
import com.mitsko.financialsystem.domain.dto.AccountResponseDto;
import com.mitsko.financialsystem.domain.dto.BankDto;
import com.mitsko.financialsystem.domain.entity.Account;
import com.mitsko.financialsystem.domain.entity.Bank;
import com.mitsko.financialsystem.exception.EntityNotFoundException;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.repository.AccountRepository;
import com.mitsko.financialsystem.repository.BankRepository;
import com.mitsko.financialsystem.repository.RepositoryFactory;
import com.mitsko.financialsystem.service.AccountService;
import com.mitsko.financialsystem.service.util.Validator;

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
