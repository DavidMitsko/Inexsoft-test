package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.service.BankService;
import com.mitsko.financialsystem.service.ServiceFactory;

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
