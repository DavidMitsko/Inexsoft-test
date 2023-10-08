package com.mitsko.financialsystem.service;

import com.mitsko.financialsystem.service.impl.*;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final BankService bankService = new BankServiceImpl();
    private final ClientService clientService = new ClientServiceImpl();
    private final AccountService accountService = new AccountServiceImpl();
    private final ExchangeRateService exchangeRateService = new ExchangeRateServiceImpl();
    private final TransactionService transactionService = new TransactionServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public BankService bankService() {
        return bankService;
    }

    public ClientService clientService() {
        return clientService;
    }

    public AccountService accountService() {
        return accountService;
    }

    public ExchangeRateService exchangeRateService() {
        return exchangeRateService;
    }

    public TransactionService transactionService() {
        return transactionService;
    }


}
