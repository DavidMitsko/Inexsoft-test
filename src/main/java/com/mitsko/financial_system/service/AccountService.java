package com.mitsko.financial_system.service;

import com.mitsko.financial_system.domain.dto.AccountDto;
import com.mitsko.financial_system.domain.dto.AccountResponseDto;
import com.mitsko.financial_system.exception.ValidationException;

import java.util.List;

public interface AccountService {

    void add(AccountDto dto) throws ValidationException;

    List<AccountResponseDto> getAccountsByClientUuid(String clientUuid);

}
