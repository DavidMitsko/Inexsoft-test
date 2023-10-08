package com.mitsko.financial_system.service;

import com.mitsko.financial_system.domain.dto.AccountDto;
import com.mitsko.financial_system.exception.ValidationException;

public interface AccountService {

    void add(AccountDto dto) throws ValidationException;

}
