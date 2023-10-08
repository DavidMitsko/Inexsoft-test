package com.mitsko.financial_system.controller.converter;

import com.mitsko.financial_system.domain.dto.*;
import com.mitsko.financial_system.domain.enums.ClientType;
import com.mitsko.financial_system.domain.enums.Currency;
import com.mitsko.financial_system.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public static ClientDto getClientDto(String parameters) throws ValidationException {
        try {
            ClientDto dto = new ClientDto();

            dto.setName(parameters.substring(0, parameters.indexOf(PARAM_DELIMITER)));
            parameters = parameters.substring(parameters.indexOf(PARAM_DELIMITER) + 1);

            dto.setSurname(parameters.substring(0, parameters.indexOf(PARAM_DELIMITER)));
            parameters = parameters.substring(parameters.indexOf(PARAM_DELIMITER) + 1);

            dto.setAge(Integer.parseInt(parameters.substring(0, parameters.indexOf(PARAM_DELIMITER))));
            parameters = parameters.substring(parameters.indexOf(PARAM_DELIMITER) + 1);

            dto.setClientType(ClientType.valueOf(parameters));

            return dto;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    public static AccountDto getAccountDto(String parameters) throws ValidationException {
        try {
            AccountDto dto = new AccountDto();

            dto.setClientUuid(parameters.substring(0, parameters.indexOf(PARAM_DELIMITER)));
            parameters = parameters.substring(parameters.indexOf(PARAM_DELIMITER) + 1);

            dto.setBankUuid(parameters.substring(0, parameters.indexOf(PARAM_DELIMITER)));
            parameters = parameters.substring(parameters.indexOf(PARAM_DELIMITER) + 1);

            dto.setCurrency(Currency.valueOf(parameters.substring(0, parameters.indexOf(PARAM_DELIMITER))));
            parameters = parameters.substring(parameters.indexOf(PARAM_DELIMITER) + 1);

            dto.setBalance(new BigDecimal(parameters));

            return dto;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    public static TransactionDto getTransactionDto(String parameters) {
        try {
            TransactionDto dto = new TransactionDto();

            dto.setSenderAccountUuid(parameters.substring(0, parameters.indexOf(PARAM_DELIMITER)));
            parameters = parameters.substring(parameters.indexOf(PARAM_DELIMITER) + 1);

            dto.setRecipientAccountUuid(parameters.substring(0, parameters.indexOf(PARAM_DELIMITER)));
            parameters = parameters.substring(parameters.indexOf(PARAM_DELIMITER) + 1);

            dto.setAmount(new BigDecimal(parameters));

            return dto;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

    public static TransactionSearchDto getTransactionSearchDto(String parameters) {
        try {
            TransactionSearchDto dto = new TransactionSearchDto();

            if (!parameters.contains(PARAM_DELIMITER)) {
                dto.setClientUuid(parameters);
            } else {
                dto.setClientUuid(parameters.substring(0, parameters.indexOf(PARAM_DELIMITER)));
                parameters = parameters.substring(parameters.indexOf(PARAM_DELIMITER) + 1);

                dto.setStartTime(LocalDateTime.parse(parameters.substring(0, parameters.indexOf(PARAM_DELIMITER))));
                parameters = parameters.substring(parameters.indexOf(PARAM_DELIMITER) + 1);

                dto.setEndTime(LocalDateTime.parse(parameters));
            }

            return dto;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ValidationException(e.getMessage());
        }
    }

}
