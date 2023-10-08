package com.mitsko.financial_system.controller.command.impl;

import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.controller.converter.Converter;
import com.mitsko.financial_system.domain.dto.TransactionDto;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.service.ServiceFactory;
import com.mitsko.financial_system.service.TransactionService;

public class TransactionCommand implements Command {

    private final TransactionService transactionService;

    public TransactionCommand() {
        this.transactionService = ServiceFactory.getInstance().transactionService();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        TransactionDto transactionDto = Converter.getTransactionDto(parameters);
        transactionService.transaction(transactionDto);
    }
}
