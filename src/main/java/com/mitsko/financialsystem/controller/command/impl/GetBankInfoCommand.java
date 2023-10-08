package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.domain.dto.BankDto;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.service.BankService;
import com.mitsko.financialsystem.service.ServiceFactory;
import com.mitsko.financialsystem.view.ViewProvider;

import java.util.List;

public class GetBankInfoCommand implements Command {

    private final BankService bankService;
    private final ViewProvider viewProvider;

    public GetBankInfoCommand() {
        this.bankService = ServiceFactory.getInstance().bankService();
        this.viewProvider = ViewProvider.getInstance();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        List<BankDto> list = bankService.getAll();

        viewProvider.getView(commandName).printResult(list);

    }
}
