package com.mitsko.financial_system.controller.command.impl;

import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.controller.converter.Converter;
import com.mitsko.financial_system.domain.dto.ClientDto;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.service.ClientService;
import com.mitsko.financial_system.service.ServiceFactory;

public class AddClientCommand implements Command {

    private final ClientService clientService;

    public AddClientCommand() {
        this.clientService = ServiceFactory.getInstance().clientService();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        ClientDto dto = Converter.getClientDto(parameters);
        clientService.add(dto);
    }
}
