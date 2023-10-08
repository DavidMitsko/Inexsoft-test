package com.mitsko.financial_system.controller.command.impl;

import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.domain.dto.ExchangeRateDto;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.service.ExchangeRateService;
import com.mitsko.financial_system.service.ServiceFactory;
import com.mitsko.financial_system.view.ViewProvider;

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
