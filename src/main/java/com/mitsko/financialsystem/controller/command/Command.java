package com.mitsko.financialsystem.controller.command;

import com.mitsko.financialsystem.exception.ValidationException;

public interface Command {

    void execute(String parameters, String commandName) throws ValidationException;

}
