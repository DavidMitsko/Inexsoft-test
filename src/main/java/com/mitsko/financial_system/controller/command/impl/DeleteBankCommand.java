package com.mitsko.financial_system.controller.command.impl;

import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.service.BankService;
import com.mitsko.financial_system.service.ServiceFactory;

public class DeleteBankCommand implements Command {

    private final BankService bankService;

    public DeleteBankCommand() {
        this.bankService = ServiceFactory.getInstance().bankService();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        bankService.deleteByUuid(parameters);
    }
}
