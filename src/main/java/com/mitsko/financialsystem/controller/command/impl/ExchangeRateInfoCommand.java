package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.domain.dto.ExchangeRateDto;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.service.ExchangeRateService;
import com.mitsko.financialsystem.service.ServiceFactory;
import com.mitsko.financialsystem.view.ViewProvider;

import java.util.List;

public class ExchangeRateInfoCommand implements Command {

    private final ExchangeRateService exchangeRateService;
    private final ViewProvider viewProvider;

    public ExchangeRateInfoCommand() {
        this.exchangeRateService = ServiceFactory.getInstance().exchangeRateService();
        this.viewProvider = ViewProvider.getInstance();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        List<ExchangeRateDto> list = exchangeRateService.list();
        viewProvider.getView(commandName).printResult(list);
    }
}
