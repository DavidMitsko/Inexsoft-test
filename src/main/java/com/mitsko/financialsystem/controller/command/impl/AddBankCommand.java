package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.controller.converter.Converter;
import com.mitsko.financialsystem.domain.dto.BankDto;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.service.BankService;
import com.mitsko.financialsystem.service.ServiceFactory;

public class AddBankCommand implements Command {

    private final BankService bankService;

    public AddBankCommand() {
        this.bankService = ServiceFactory.getInstance().bankService();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        BankDto dto = Converter.getBankDto(parameters);
        bankService.add(dto);
    }
}
