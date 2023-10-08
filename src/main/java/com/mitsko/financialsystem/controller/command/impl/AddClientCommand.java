package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.controller.converter.Converter;
import com.mitsko.financialsystem.domain.dto.ClientDto;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.service.ClientService;
import com.mitsko.financialsystem.service.ServiceFactory;

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
