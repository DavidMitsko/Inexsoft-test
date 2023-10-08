package com.mitsko.financial_system.service;

import com.mitsko.financial_system.domain.dto.BankDto;
import com.mitsko.financial_system.exception.ValidationException;

import java.util.List;

public interface BankService {

    void add(BankDto createDto) throws ValidationException;

    List<BankDto> getAll();

    void deleteByUuid(String uuid);

}
