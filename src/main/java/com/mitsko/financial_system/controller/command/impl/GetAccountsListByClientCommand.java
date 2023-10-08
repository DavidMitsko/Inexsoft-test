package com.mitsko.financial_system.controller.command.impl;

import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.domain.dto.AccountResponseDto;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.service.AccountService;
import com.mitsko.financial_system.service.ServiceFactory;
import com.mitsko.financial_system.view.ViewProvider;

import java.util.List;

public class GetAccountsListByClientCommand implements Command {

    private final AccountService accountService;
    private final ViewProvider viewProvider;

    public GetAccountsListByClientCommand() {
        this.accountService = ServiceFactory.getInstance().accountService();
        this.viewProvider = ViewProvider.getInstance();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        List<AccountResponseDto> list = accountService.getAccountsByClientUuid(parameters);
        viewProvider.getView(commandName).printResult(list);
    }
}
