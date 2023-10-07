package com.mitsko.financial_system.controller;

import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.controller.command.CommandProvider;
import com.mitsko.financial_system.domain.enums.CommandName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

import static com.mitsko.financial_system.constant.Constants.PARAM_DELIMITER;

public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    private static final Controller instance = new Controller();

    private final CommandProvider commandProvider = new CommandProvider();

    public static Controller getInstance() {
        return instance;
    }

    private boolean execute = true;

    public void run() {
        Scanner input = new Scanner(System.in);
        while (execute) {
            String request = input.nextLine();

            String commandName = null;
            Command executionCommand;

            if (request.contains(PARAM_DELIMITER)) {
                commandName = request.substring(0, request.indexOf(PARAM_DELIMITER));
                request = request.substring(request.indexOf(PARAM_DELIMITER) + 1);
            } else {
                commandName = request;
            }

            executionCommand = commandProvider.getCommand(commandName);

            try {
                executionCommand.execute(request, commandName);

                if (commandName.equals(CommandName.EXIT.getCommand())) {
                    setExecute(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void setExecute(boolean execute) {
        this.execute = execute;
    }
}
