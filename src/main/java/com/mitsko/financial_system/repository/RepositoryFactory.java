package com.mitsko.financial_system.repository;

import com.mitsko.financial_system.repository.impl.BankRepositoryImpl;
import com.mitsko.financial_system.repository.impl.ClientRepositoryImpl;

public class RepositoryFactory {

    private static final RepositoryFactory instance = new RepositoryFactory();

    private final BankRepository bankRepository = new BankRepositoryImpl();
    private final ClientRepository clientRepository = new ClientRepositoryImpl();

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

}
