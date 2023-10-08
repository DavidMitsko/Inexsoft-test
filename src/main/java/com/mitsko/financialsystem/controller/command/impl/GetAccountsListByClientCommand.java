package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.domain.dto.AccountResponseDto;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.service.AccountService;
import com.mitsko.financialsystem.service.ServiceFactory;
import com.mitsko.financialsystem.view.ViewProvider;

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
