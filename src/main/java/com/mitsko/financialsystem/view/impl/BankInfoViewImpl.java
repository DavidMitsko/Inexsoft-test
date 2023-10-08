package com.mitsko.financialsystem.view.impl;

import com.mitsko.financialsystem.domain.dto.BankDto;
import com.mitsko.financialsystem.view.View;

import java.util.List;

public class BankInfoViewImpl implements View<List<BankDto>> {

    @Override
    public void printResult(List<BankDto> list) {
        System.out.println("Banks info:");

        int i = 1;
        for (BankDto dto : list) {
            System.out.printf("%s. Uuid: %s. Name: %s. Individuals commission: %s%%. Legal entities commission: %s%%.%n",
                    i, dto.getUuid(), dto.getName(), dto.getIndividualsCommission(), dto.getLegalEntitiesCommission());

            i++;
        }
    }

}
