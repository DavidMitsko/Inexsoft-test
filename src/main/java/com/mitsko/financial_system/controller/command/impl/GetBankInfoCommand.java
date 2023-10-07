package com.mitsko.financial_system.controller.command.impl;

import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.domain.dto.bank.BankDto;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.service.BankService;
import com.mitsko.financial_system.service.ServiceFactory;
import com.mitsko.financial_system.view.ViewProvider;

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
