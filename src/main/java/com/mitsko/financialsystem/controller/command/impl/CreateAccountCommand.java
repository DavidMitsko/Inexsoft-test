package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.controller.converter.Converter;
import com.mitsko.financialsystem.domain.dto.AccountDto;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.service.AccountService;
import com.mitsko.financialsystem.service.ServiceFactory;

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
