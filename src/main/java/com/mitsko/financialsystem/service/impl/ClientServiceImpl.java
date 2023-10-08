package com.mitsko.financialsystem.service.impl;

import com.mitsko.financialsystem.domain.dto.ClientDto;
import com.mitsko.financialsystem.domain.entity.Account;
import com.mitsko.financialsystem.domain.entity.Client;
import com.mitsko.financialsystem.exception.ValidationException;
import com.mitsko.financialsystem.repository.AccountRepository;
import com.mitsko.financialsystem.repository.ClientRepository;
import com.mitsko.financialsystem.repository.RepositoryFactory;
import com.mitsko.financialsystem.repository.TransactionRepository;
import com.mitsko.financialsystem.service.ClientService;
import com.mitsko.financialsystem.service.util.Validator;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.getAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void updateClient(ClientDto dto, String clientUuid) {
        if (Validator.validateAge(dto.getAge()) || !Validator.validateUuid(clientUuid)) {
            throw new ValidationException("Wrong age");
        }

        Client client = new Client(dto.getName(), dto.getSurname(), dto.getAge(), dto.getClientType());
        clientRepository.updateByUuid(client, clientUuid);
    }

    private ClientDto toDto(Client client) {
        ClientDto dto = new ClientDto();

        dto.setUuid(client.getUuid());
        dto.setName(client.getName());
        dto.setSurname(client.getSurname());
        dto.setAge(client.getAge());
        dto.setClientType(client.getClientType());

        return dto;
    }
}
