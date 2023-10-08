package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.domain.enums.CommandName;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.view.ViewProvider;

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
