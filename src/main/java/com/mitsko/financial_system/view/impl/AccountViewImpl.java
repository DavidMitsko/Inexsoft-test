package com.mitsko.financial_system.view.impl;

import com.mitsko.financial_system.domain.dto.AccountResponseDto;
import com.mitsko.financial_system.view.View;

import java.util.List;

public class AccountViewImpl implements View<List<AccountResponseDto>> {

    @Override
    public void printResult(List<AccountResponseDto> accountResponseDtos) {
        System.out.println("Accounts info:");

        int i = 1;
        for (AccountResponseDto dto: accountResponseDtos) {
            System.out.printf("%s. Account uuid: %s. Bank name: %s. Account balance %s. Account currency: %s",
                    i, dto.getUuid(), dto.getBank().getName(), dto.getBalance(), dto.getCurrency());

            i++;
        }
    }
}
