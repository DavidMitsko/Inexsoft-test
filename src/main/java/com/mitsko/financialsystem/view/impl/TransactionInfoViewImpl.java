package com.mitsko.financialsystem.view.impl;

import com.mitsko.financialsystem.domain.dto.TransactionResponseDto;
import com.mitsko.financialsystem.view.View;

import java.util.List;

public class TransactionInfoViewImpl implements View<List<TransactionResponseDto>> {

    @Override
    public void printResult(List<TransactionResponseDto> transactionResponseDtos) {
        System.out.println("Transactions info:");

        int i = 1;
        for (TransactionResponseDto dto: transactionResponseDtos) {
            System.out.printf("%s. Transaction time: %s. Sender: %s %s. Recipient: %s %s. Total amount: %s. Commission: %s%n",
                    i, dto.getTransactionTime(), dto.getSender().getName(), dto.getSender().getSurname(),
                    dto.getRecipient().getName(), dto.getRecipient().getSurname(), dto.getAmount(), dto.getCommission());
        }
    }
}
