package com.mitsko.financial_system.service.impl;

import com.mitsko.financial_system.domain.dto.TransactionDto;
import com.mitsko.financial_system.domain.entity.*;
import com.mitsko.financial_system.domain.enums.ClientType;
import com.mitsko.financial_system.exception.EntityNotFoundException;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.repository.*;
import com.mitsko.financial_system.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;

    public TransactionServiceImpl() {
        this.transactionRepository = RepositoryFactory.getInstance().transactionRepository();
        this.accountRepository = RepositoryFactory.getInstance().accountRepository();
        this.exchangeRateRepository = RepositoryFactory.getInstance().exchangeRateRepository();
        this.clientRepository = RepositoryFactory.getInstance().getClientRepository();
        this.bankRepository = RepositoryFactory.getInstance().getBankRepository();
    }

    @Override
    public void transaction(TransactionDto dto) {
        Account senderAccount = accountRepository.getByUuid(dto.getSenderAccountUuid())
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Account with %s uuid can not be found", dto.getSenderAccountUuid())));

        if (senderAccount.getBalance().compareTo(dto.getAmount()) < 0) {
            logger.info("Insufficient funds, account {}", dto.getSenderAccountUuid());
            throw new ValidationException("Insufficient funds");
        }

        Account recipientAccount = accountRepository.getByUuid(dto.getRecipientAccountUuid())
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Account with %s uuid can not be found", dto.getSenderAccountUuid())));

        BigDecimal amount = calculateAmountByCommission(senderAccount, recipientAccount, dto.getAmount());

        BigDecimal commission = dto.getAmount().subtract(amount);

        amount = calculateAmountByCurrency(senderAccount, recipientAccount, amount);

        logger.info("Amount for recipient: {}. Amount for sender: {}. Commission for sender: {}",
                amount, dto.getAmount(), commission);

        Transaction transaction = Transaction.builder()
                .setUuid(UUID.randomUUID().toString())
                .setTransactionTime(LocalDateTime.now())
                .setCommission(commission)
                .setAmount(dto.getAmount())
                .setSenderAccountUuid(dto.getSenderAccountUuid())
                .setRecipientAccountUuid(dto.getRecipientAccountUuid())
                .build();

        senderAccount.setBalance(senderAccount.getBalance().subtract(dto.getAmount()));
        recipientAccount.setBalance(recipientAccount.getBalance().add(amount));

        transactionRepository.save(transaction);
        accountRepository.updateByUuid(senderAccount, dto.getSenderAccountUuid());
        accountRepository.updateByUuid(recipientAccount, dto.getRecipientAccountUuid());
    }

    private BigDecimal calculateAmountByCurrency(Account sender, Account recipient, BigDecimal amount) {
        if (sender.getCurrency() == recipient.getCurrency()) {
            return amount;
        }
        ExchangeRate exchangeRate = exchangeRateRepository.getByCurrencies(sender.getCurrency(), recipient.getCurrency())
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Exchange rate for %s and %s can not be found", sender.getCurrency(), recipient.getCurrency())));

        if (exchangeRate.getFirstCurrency() == sender.getCurrency()) {
            amount = amount.multiply(exchangeRate.getRate());
        } else {
            amount = amount.divide(exchangeRate.getRate(), 2, RoundingMode.DOWN);
        }
        return amount;
    }

    private BigDecimal calculateAmountByCommission(Account sender, Account recipient, BigDecimal amount) {
        if (sender.getBankUuid().equals(recipient.getBankUuid())) {
            return amount;
        }
        Client senderClient = clientRepository.getByUuid(sender.getClientUuid())
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Client with %s uuid can not be found", sender.getClientUuid())));

        Bank senderBank = bankRepository.getByUuid(sender.getBankUuid())
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Bank with %s uuid can not be found", sender.getBankUuid())));

        int percent;
        if (senderClient.getClientType() == ClientType.INDIVIDUAL) {
            percent = 100 - senderBank.getIndividualsCommission();
        } else {
            percent = 100 - senderBank.getLegalEntitiesCommission();
        }
        return amount.multiply(new BigDecimal(percent))
                .divide(new BigDecimal(100), 2, RoundingMode.DOWN);
    }
}
