package com.mitsko.financialsystem.controller;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.controller.command.CommandProvider;
import com.mitsko.financialsystem.domain.enums.CommandName;
import com.mitsko.financialsystem.view.impl.ExceptionView;

import java.util.Scanner;

import static com.mitsko.financialsystem.constant.Constants.PARAM_DELIMITER;

public class Controller {

    private static final Controller instance = new Controller();

    private final CommandProvider commandProvider = new CommandProvider();

    public static Controller getInstance() {
        return instance;
    }

    private boolean execute = true;

    public void run() {
        Scanner input = new Scanner(System.in);
        while (execute) {
            System.out.println("Write your command:");
            String request = input.nextLine();

            String commandName;
            Command executionCommand;

            if (request.contains(PARAM_DELIMITER)) {
                commandName = request.substring(0, request.indexOf(PARAM_DELIMITER));
                request = request.substring(request.indexOf(PARAM_DELIMITER) + 1);
            } else {
                commandName = request;
            }

            try {
                executionCommand = commandProvider.getCommand(commandName);
                executionCommand.execute(request, commandName);

                if (commandName.equals(CommandName.EXIT.getCommand())) {
                    setExecute(false);
                }
            } catch (RuntimeException e) {
                ExceptionView view = new ExceptionView();
                view.printResult(e);
            }

        }
    }

    public void setExecute(boolean execute) {
        this.execute = execute;
    }
}
