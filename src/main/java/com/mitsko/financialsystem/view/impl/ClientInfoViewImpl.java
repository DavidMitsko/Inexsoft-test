package com.mitsko.financialsystem.view.impl;

import com.mitsko.financialsystem.domain.dto.ClientDto;
import com.mitsko.financialsystem.view.View;

import java.util.List;

public class ClientInfoViewImpl implements View<List<ClientDto>> {

    @Override
    public void printResult(List<ClientDto> clientDtos) {
        System.out.println("Client info:");

        int i = 1;
        for (ClientDto dto : clientDtos) {
            System.out.printf("%s. Uuid: %s. Name: %s. Surname: %s. Age: %s. Client type: %s",
                    i, dto.getUuid(), dto.getName(), dto.getSurname(), dto.getAge(), dto.getClientType());
        }
    }
}
