package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.controller.converter.Converter;
import com.mitsko.financialsystem.domain.dto.TransactionDto;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.service.ServiceFactory;
import com.mitsko.financialsystem.service.TransactionService;

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
