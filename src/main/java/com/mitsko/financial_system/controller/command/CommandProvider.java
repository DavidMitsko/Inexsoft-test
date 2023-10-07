package com.mitsko.financial_system.controller.command;

import com.mitsko.financial_system.controller.command.impl.AddBankCommand;
import com.mitsko.financial_system.controller.command.impl.ExitCommand;
import com.mitsko.financial_system.controller.command.impl.GetBankInfoCommand;
import com.mitsko.financial_system.domain.enums.CommandName;
import com.mitsko.financial_system.exception.ValidationException;
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
    }

    public Command getCommand(String name) throws ValidationException {
        CommandName commandName = null;
        Command command = null;

        try {

            commandName = Arrays.stream(CommandName.values()).filter(c -> c.getCommand().equals(name))
                    .findFirst().orElseThrow(() -> new ValidationException("Wrong command"));

            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException | ValidationException e) {
            logger.error(e.getMessage());
            throw new ValidationException(e);
        }

        return command;
    }

}
