package com.mitsko.financial_system.repository;

import com.mitsko.financial_system.repository.impl.BankRepositoryImpl;

public class RepositoryFactory {

    private static final RepositoryFactory instance = new RepositoryFactory();

    private BankRepository bankRepository = new BankRepositoryImpl();

    private RepositoryFactory() {}

    public static RepositoryFactory getInstance() {
        return instance;
    }

    public BankRepository getBankRepository() {
        return bankRepository;
    }

}
