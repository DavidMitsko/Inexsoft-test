package com.mitsko.financial_system.domain.enums;

public enum CommandName {
    GET_ALL_BANKS("banks", "Get information about all banks."),
    ADD_NEW_BANK("add_bank", "Create new bank in app. Parameters: name, individuals commission (percent value)," +
            " legal entities commission (percent value)."),
    EXIT("exit", "Finish program.");

    private final String command;

    private final String description;

    CommandName(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
