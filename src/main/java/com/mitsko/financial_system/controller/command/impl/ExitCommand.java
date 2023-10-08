package com.mitsko.financial_system.controller.command.impl;

import com.mitsko.financial_system.controller.Controller;
import com.mitsko.financial_system.controller.command.Command;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.repository.connection_pool.ConnectionPool;
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
