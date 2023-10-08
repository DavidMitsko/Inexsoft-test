package com.mitsko.financialsystem.controller.command;

import com.mitsko.financialsystem.controller.command.impl.*;
import com.mitsko.financialsystem.domain.enums.CommandName;
import com.mitsko.financialsystem.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static final Logger logger = LoggerFactory.getLogger(CommandProvider.class);

    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider(){
        repository.put(CommandName.GET_ALL_BANKS, new GetBankInfoCommand());
        repository.put(CommandName.ADD_NEW_BANK, new AddBankCommand());
        repository.put(CommandName.EXIT, new ExitCommand());
        repository.put(CommandName.CREATE_NEW_CLIENT, new AddClientCommand());
        repository.put(CommandName.CREATE_ACCOUNT, new CreateAccountCommand());
        repository.put(CommandName.GET_CLIENTS_ACCOUNTS, new GetAccountsListByClientCommand());
        repository.put(CommandName.HELP, new HelpCommand());
        repository.put(CommandName.GET_EXCHANGE_RATE, new ExchangeRateInfoCommand());
        repository.put(CommandName.TRANSACTION, new TransactionCommand());
        repository.put(CommandName.GET_TRANSACTIONS, new TransactionInfoCommand());
        repository.put(CommandName.DELETE_BANK, new DeleteBankCommand());
        repository.put(CommandName.DELETE_CLIENT, new DeleteClientCommand());
        repository.put(CommandName.GET_CLIENTS, new ClientInfoCommand());
        repository.put(CommandName.EDIT_CLIENT, new EditClientInfoCommand());
        repository.put(CommandName.EDIT_BANK, new EditBankInfoCommand());
    }

    public Command getCommand(String name) throws ValidationException {
        CommandName commandName;
        Command command;

        try {

            commandName = Arrays.stream(CommandName.values()).filter(c -> c.getCommand().equals(name))
                    .findFirst().orElseThrow(() -> new ValidationException("Wrong command"));

            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            logger.error(e.getMessage());
            throw new ValidationException(e);
        }

        return command;
    }

}
