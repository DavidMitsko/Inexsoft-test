package com.mitsko.financial_system.service;

import com.mitsko.financial_system.service.impl.BankServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final BankService bankService = new BankServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public BankService bankService() {
        return bankService;
    }


}
