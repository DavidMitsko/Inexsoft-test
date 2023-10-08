package com.mitsko.financialsystem.service;

import com.mitsko.financialsystem.domain.dto.AccountDto;
import com.mitsko.financialsystem.domain.dto.AccountResponseDto;
import com.mitsko.financialsystem.exception.ValidationException;

import java.util.List;

public interface AccountService {

    void add(AccountDto dto) throws ValidationException;

    List<AccountResponseDto> getAccountsByClientUuid(String clientUuid);

}
