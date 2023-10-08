package com.mitsko.financial_system.service.impl;

import com.mitsko.financial_system.domain.dto.ClientDto;
import com.mitsko.financial_system.domain.entity.Account;
import com.mitsko.financial_system.domain.entity.Client;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.repository.AccountRepository;
import com.mitsko.financial_system.repository.ClientRepository;
import com.mitsko.financial_system.repository.RepositoryFactory;
import com.mitsko.financial_system.repository.TransactionRepository;
import com.mitsko.financial_system.service.ClientService;
import com.mitsko.financial_system.service.util.Validator;

import java.util.List;
import java.util.UUID;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public ClientServiceImpl() {
        this.clientRepository = RepositoryFactory.getInstance().getClientRepository();
        this.accountRepository = RepositoryFactory.getInstance().accountRepository();
        this.transactionRepository = RepositoryFactory.getInstance().transactionRepository();
    }

    @Override
    public void add(ClientDto dto) throws ValidationException {
        if (Validator.validateAge(dto.getAge())) {
            throw new ValidationException("Wrong age");
        }
        Client client = new Client(dto.getName(), dto.getSurname(), dto.getAge(), dto.getClientType());
        clientRepository.save(client);
    }

    @Override
    public void delete(String clientUuid) {
        if (!Validator.validateUuid(clientUuid)) {
            throw new ValidationException("Invalid uuid");
        }

        List<Account> accounts = accountRepository.getAllByClientUuid(clientUuid);

        String transactionId = UUID.randomUUID().toString();
        accounts.forEach(account -> {
            transactionRepository.deleteByAccountUuid(account.getUuid(),
                    true, transactionId, false);
            accountRepository.deleteByUuid(account.getUuid(), true, transactionId, false);
        });

        clientRepository.deleteByUuid(clientUuid, true, transactionId, true);

    }
}
