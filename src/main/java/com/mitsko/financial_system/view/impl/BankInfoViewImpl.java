package com.mitsko.financial_system.view.impl;

import com.mitsko.financial_system.domain.dto.BankDto;
import com.mitsko.financial_system.view.View;

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
