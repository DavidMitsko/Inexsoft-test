package com.mitsko.financial_system.repository;

import com.mitsko.financial_system.repository.impl.*;

public class RepositoryFactory {

    private static final RepositoryFactory instance = new RepositoryFactory();

    private final BankRepository bankRepository = new BankRepositoryImpl();
    private final ClientRepository clientRepository = new ClientRepositoryImpl();
    private final AccountRepository accountRepository = new AccountRepositoryImpl();
    private final TransactionRepository transactionRepository = new TransactionRepositoryImpl();
    private final ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepositoryImpl();

    private RepositoryFactory() {}

    public static RepositoryFactory getInstance() {
        return instance;
    }

    public BankRepository getBankRepository() {
        return bankRepository;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public AccountRepository accountRepository() {
        return accountRepository;
    }

    public TransactionRepository transactionRepository() {
        return transactionRepository;
    }

    public ExchangeRateRepository exchangeRateRepository() {
        return exchangeRateRepository;
    }

}
