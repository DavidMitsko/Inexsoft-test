package com.mitsko.financial_system.service.impl;

import com.mitsko.financial_system.domain.dto.AccountDto;
import com.mitsko.financial_system.domain.entity.Account;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.repository.AccountRepository;
import com.mitsko.financial_system.repository.RepositoryFactory;
import com.mitsko.financial_system.service.AccountService;
import com.mitsko.financial_system.service.util.Validator;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl() {
        this.accountRepository = RepositoryFactory.getInstance().accountRepository();
    }

    @Override
    public void add(AccountDto dto) throws ValidationException {
        if (Validator.validateAmount(dto.getBalance())) {
            throw new ValidationException("Balance can not be negative");
        }

        Account account = new Account(dto.getBalance(), dto.getClientUuid(), dto.getBankUuid(), dto.getCurrency());
        accountRepository.save(account);

    }
}
