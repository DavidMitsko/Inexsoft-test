package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.controller.converter.Converter;
import com.mitsko.financialsystem.domain.dto.TransactionResponseDto;
import com.mitsko.financialsystem.domain.dto.TransactionSearchDto;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.service.ServiceFactory;
import com.mitsko.financialsystem.service.TransactionService;
import com.mitsko.financialsystem.view.ViewProvider;

import java.util.List;

public class TransactionInfoCommand implements Command {

    private final TransactionService transactionService;
    private final ViewProvider viewProvider;

    public TransactionInfoCommand() {
        this.transactionService = ServiceFactory.getInstance().transactionService();
        this.viewProvider = ViewProvider.getInstance();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        TransactionSearchDto searchDto = Converter.getTransactionSearchDto(parameters);
        List<TransactionResponseDto> list = transactionService.getTransactionByClient(searchDto);

        viewProvider.getView(commandName).printResult(list);
    }
}
