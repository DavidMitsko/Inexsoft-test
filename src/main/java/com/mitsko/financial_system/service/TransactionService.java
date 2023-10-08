package com.mitsko.financial_system.service;

import com.mitsko.financial_system.domain.dto.TransactionDto;
import com.mitsko.financial_system.domain.dto.TransactionResponseDto;
import com.mitsko.financial_system.domain.dto.TransactionSearchDto;

import java.util.List;

public interface TransactionService {

    void transaction(TransactionDto dto);

    List<TransactionResponseDto> getTransactionByClient(TransactionSearchDto searchDto);

}
