package com.mitsko.financial_system.controller.command.impl;

import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.controller.converter.Converter;
import com.mitsko.financial_system.domain.dto.AccountDto;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.service.AccountService;
import com.mitsko.financial_system.service.ServiceFactory;

public class CreateAccountCommand implements Command {

    private final AccountService accountService;

    public CreateAccountCommand() {
        this.accountService = ServiceFactory.getInstance().accountService();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        AccountDto dto = Converter.getAccountDto(parameters);
        accountService.add(dto);
    }
}
