package com.mitsko.financial_system.controller.command.impl;

import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.controller.converter.Converter;
import com.mitsko.financial_system.domain.dto.TransactionResponseDto;
import com.mitsko.financial_system.domain.dto.TransactionSearchDto;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.service.ServiceFactory;
import com.mitsko.financial_system.service.TransactionService;
import com.mitsko.financial_system.view.ViewProvider;

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
