package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.repository.connectionpool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExitCommand implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ExitCommand.class);

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        logger.info("Finish program");
        ConnectionPool.getInstance().dispose();
    }
}
