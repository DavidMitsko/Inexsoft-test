package com.mitsko.financial_system.controller.command.impl;

import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.service.ClientService;
import com.mitsko.financial_system.service.ServiceFactory;

public class DeleteClientCommand implements Command {

    private final ClientService clientService;

    public DeleteClientCommand() {
        this.clientService = ServiceFactory.getInstance().clientService();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        clientService.delete(parameters);
    }
}
