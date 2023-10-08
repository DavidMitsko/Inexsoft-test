package com.mitsko.financial_system.view.impl;

import com.mitsko.financial_system.domain.enums.CommandName;
import com.mitsko.financial_system.view.View;

import java.util.List;

public class HelpInfoViewImpl implements View<List<CommandName>> {

    @Override
    public void printResult(List<CommandName> commandNames) {
        System.out.println("Commands info:");
        commandNames.forEach(commandName -> System.out.printf("Command: %s. Description: %s%n",
                commandName.getCommand(), commandName.getDescription()));
    }
}
