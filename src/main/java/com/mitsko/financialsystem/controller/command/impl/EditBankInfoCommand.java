package com.mitsko.financialsystem.controller.command.impl;

import com.mitsko.financialsystem.controller.command.Command;
import com.mitsko.financialsystem.controller.converter.Converter;
import com.mitsko.financialsystem.domain.dto.BankDto;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.service.BankService;
import com.mitsko.financialsystem.service.ServiceFactory;

import static com.mitsko.financialsystem.constant.Constants.PARAM_DELIMITER;

public class EditBankInfoCommand implements Command {

    private final BankService bankService;

    public EditBankInfoCommand() {
        this.bankService = ServiceFactory.getInstance().bankService();
    }

    @Override
    public void execute(String parameters, String commandName) throws ValidationException {
        BankDto bankDto = Converter.getBankDto(parameters);
        bankService.updateBankInfo(bankDto, parameters.substring(parameters.lastIndexOf(PARAM_DELIMITER) + 1));
    }
}
