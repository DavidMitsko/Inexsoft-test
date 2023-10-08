package com.mitsko.financialsystem.view.impl;

import com.mitsko.financialsystem.domain.dto.AccountResponseDto;
import com.mitsko.financialsystem.view.View;

import java.util.List;

public class AccountViewImpl implements View<List<AccountResponseDto>> {

    @Override
    public void printResult(List<AccountResponseDto> accountResponseDtos) {
        System.out.println("Accounts info:");

        int i = 1;
        for (AccountResponseDto dto: accountResponseDtos) {
            System.out.printf("%s. Account uuid: %s. Bank name: %s. Account balance %s. Account currency: %s%n",
                    i, dto.getUuid(), dto.getBank().getName(), dto.getBalance(), dto.getCurrency());

            i++;
        }
    }
}
