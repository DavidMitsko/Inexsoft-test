package com.mitsko.financial_system.domain.enums;

public enum CommandName {
    GET_ALL_BANKS("banks", "Get information about all banks."),
    ADD_NEW_BANK("add_bank", "Create new bank in app. Parameters: name, individuals commission (percent value)," +
            " legal entities commission (percent value)."),
    EXIT("exit", "Finish program."),
    CREATE_NEW_CLIENT("add_client", "Create new client. Parameters: name, surname, age (>0), client type (INDIVIDUAL/LEGAL_ENTITY)."),
    CREATE_ACCOUNT("create_account", "Create account for client. Parameters: client uuid, bank uuid, currency, amount."),
    GET_CLIENTS_ACCOUNTS("accounts", "Get information about all clients accounts. Parameters: client uuid."),
    HELP("help", "Show all commands."),
    GET_EXCHANGE_RATE("exchange_rate", "Show all exchange rates."),
    TRANSACTION("transaction", "Make transaction between 2 accounts. Parameters: sender account uuid," +
            "recipient account uuid, amount"),
    GET_TRANSACTIONS("get_transactions", "Get information about all clients transactions. Parameters: client uuid," +
            " start time (optional), end time (optional)"),
    DELETE_BANK("delete_bank", "Delete bank. Parameter: bank uuid");

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
