package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.domain.dto.ClientDto;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.service.ClientService;
import com.mitsko.financialsystem.service.ServiceFactory;
import com.mitsko.financialsystem.view.ViewProvider;

import java.util.List;

public class ClientInfoCommand implements Command {

    private final ClientService clientService;
    private final ViewProvider viewProvider;

    public ClientInfoCommand() {
        this.clientService = ServiceFactory.getInstance().clientService();
        this.viewProvider = ViewProvider.getInstance();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        List<ClientDto> list = clientService.getAllClients();
        viewProvider.getView(commandName).printResult(list);
    }
}
