package com.mitsko.financialsystem.service;

import com.mitsko.financialsystem.domain.dto.BankDto;
import com.mitsko.financialsystem.exception.ValidationException;

import java.util.List;

public interface BankService {

    void add(BankDto createDto) throws ValidationException;

    List<BankDto> getAll();

    void deleteByUuid(String uuid);

    void updateBankInfo(BankDto bankDto, String uuid);

}
