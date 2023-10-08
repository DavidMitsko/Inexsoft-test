package com.mitsko.financialsystem.service;

import com.mitsko.financialsystem.domain.dto.TransactionDto;
import com.mitsko.financialsystem.domain.dto.TransactionResponseDto;
import com.mitsko.financialsystem.domain.dto.TransactionSearchDto;

import java.util.List;

public interface TransactionService {

    void transaction(TransactionDto dto);

    List<TransactionResponseDto> getTransactionByClient(TransactionSearchDto searchDto);

}
