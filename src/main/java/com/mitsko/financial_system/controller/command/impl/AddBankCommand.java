package com.mitsko.financial_system.controller.command.impl;

import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.controller.converter.Converter;
import com.mitsko.financial_system.domain.dto.bank.BankDto;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.service.BankService;
import com.mitsko.financial_system.service.ServiceFactory;

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
