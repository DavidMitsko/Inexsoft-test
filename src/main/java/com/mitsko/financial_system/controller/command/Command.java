package com.mitsko.financial_system.controller.command;

import com.mitsko.financial_system.exception.ValidationException;

public interface Command {

    void execute(String parameters, String commandName) throws ValidationException;

}
