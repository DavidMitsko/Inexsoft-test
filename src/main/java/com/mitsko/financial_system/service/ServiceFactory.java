package com.mitsko.financial_system.service;

import com.mitsko.financial_system.service.impl.AccountServiceImpl;
import com.mitsko.financial_system.service.impl.BankServiceImpl;
import com.mitsko.financial_system.service.impl.ClientServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final BankService bankService = new BankServiceImpl();
    private final ClientService clientService = new ClientServiceImpl();
    private final AccountService accountService = new AccountServiceImpl();

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


}
