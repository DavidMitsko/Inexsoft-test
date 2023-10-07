package com.mitsko.financial_system.controller.converter;

import com.mitsko.financial_system.domain.dto.BankDto;
import com.mitsko.financial_system.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mitsko.financial_system.constant.Constants.PARAM_DELIMITER;

public class Converter {

    private final static Logger logger = LoggerFactory.getLogger(Converter.class);

    public static BankDto getBankDto(String parameters) throws ValidationException {
        try {
            BankDto dto = new BankDto();

            dto.setName(parameters.substring(0, parameters.indexOf(PARAM_DELIMITER)));
            parameters = parameters.substring(parameters.indexOf(PARAM_DELIMITER) + 1);

            dto.setIndividualsCommission(Integer.parseInt(parameters.substring(0, parameters.indexOf(PARAM_DELIMITER))));
            parameters = parameters.substring(parameters.indexOf(PARAM_DELIMITER) + 1);

            dto.setLegalEntitiesCommission(Integer.parseInt(parameters));

            return dto;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

}
