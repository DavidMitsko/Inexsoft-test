package com.mitsko.financial_system.controller.command.impl;

import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.domain.enums.CommandName;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.view.ViewProvider;

import java.util.List;

public class HelpCommand implements Command {

    private final ViewProvider viewProvider;

    public HelpCommand() {
        this.viewProvider = ViewProvider.getInstance();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        viewProvider.getView(commandName).printResult(List.of(CommandName.values()));
    }
}
