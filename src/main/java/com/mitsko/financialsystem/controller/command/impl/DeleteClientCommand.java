package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.service.ClientService;
import com.mitsko.financialsystem.service.ServiceFactory;

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
