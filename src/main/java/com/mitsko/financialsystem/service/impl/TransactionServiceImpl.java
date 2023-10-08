package com.mitsko.financialsystem.service.impl;

import com.mitsko.financialsystem.domain.dto.ClientDto;
import com.mitsko.financialsystem.domain.dto.TransactionDto;
import com.mitsko.financialsystem.domain.dto.TransactionResponseDto;
import com.mitsko.financialsystem.domain.dto.TransactionSearchDto;
import com.mitsko.financialsystem.domain.entity.*;
import com.mitsko.financialsystem.domain.enums.ClientType;
import com.mitsko.financialsystem.exception.EntityNotFoundException;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.repository.*;
import com.mitsko.financialsystem.service.TransactionService;
import com.mitsko.financialsystem.service.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        String transactionId = UUID.randomUUID().toString();
        transactionRepository.save(transaction, true, transactionId, false);
        accountRepository.updateByUuid(senderAccount, dto.getSenderAccountUuid(), true, transactionId, false);
        accountRepository.updateByUuid(recipientAccount, dto.getRecipientAccountUuid(), true, transactionId, true);
    }

    @Override
    public List<TransactionResponseDto> getTransactionByClient(TransactionSearchDto searchDto) {
        if (!Validator.validateUuid(searchDto.getClientUuid())) {
            throw new ValidationException("Invalid uuid");
        }
        Client senderClient = clientRepository.getByUuid(searchDto.getClientUuid())
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Client with %s uuid can not be found", searchDto.getClientUuid())));

        List<Account> accounts = accountRepository.getAllByClientUuid(senderClient.getUuid());

        List<Transaction> transactions = new LinkedList<>();
        if (searchDto.getStartTime() == null || searchDto.getEndTime() == null) {

            for (Account account : accounts) {
                transactions.addAll(transactionRepository.getBySenderUuid(account.getUuid()));
            }

        } else {
            if (!Validator.validateDates(searchDto.getStartTime(), searchDto.getEndTime())) {
                throw new ValidationException("Wrong dates");
            }

            for (Account account : accounts) {
                transactions.addAll(transactionRepository.getBySenderUuidAndTransactionTimeBetween(account.getUuid(),
                        searchDto.getStartTime(), searchDto.getEndTime()));
            }
        }

        return transactions.stream().map(transaction -> {
            Account recipientAccount = accountRepository.getByUuid(transaction.getRecipientAccountUuid())
                    .orElseThrow(() -> new EntityNotFoundException(String
                            .format("Account with %s uuid can not be found", transaction.getRecipientAccountUuid())));

            Client recipientClient = clientRepository.getByUuid(recipientAccount.getClientUuid())
                    .orElseThrow(() -> new EntityNotFoundException(String
                            .format("Client with %s uuid can not be found", recipientAccount.getClientUuid())));

            return toDto(transaction, senderClient, recipientClient);
        }).collect(Collectors.toList());
    }

    private TransactionResponseDto toDto(Transaction transaction, Client sender, Client recipient) {
        TransactionResponseDto dto = new TransactionResponseDto();

        dto.setUuid(transaction.getUuid());
        dto.setTransactionTime(transaction.getTransactionTime());
        dto.setAmount(transaction.getAmount());
        dto.setCommission(transaction.getCommission());

        ClientDto senderDto = new ClientDto();
        senderDto.setClientType(senderDto.getClientType());
        senderDto.setName(sender.getName());
        senderDto.setSurname(sender.getSurname());
        senderDto.setAge(sender.getAge());

        ClientDto recipientDto = new ClientDto();
        recipientDto.setClientType(recipient.getClientType());
        recipientDto.setName(recipient.getName());
        recipientDto.setSurname(recipient.getSurname());
        recipientDto.setAge(recipient.getAge());

        dto.setSender(senderDto);
        dto.setRecipient(recipientDto);

        return dto;
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
