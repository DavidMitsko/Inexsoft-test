package com.mitsko.financial_system.service.impl;

import com.mitsko.financial_system.domain.dto.ClientDto;
import com.mitsko.financial_system.domain.entity.Client;
import com.mitsko.financial_system.exception.ValidationException;
import com.mitsko.financial_system.repository.ClientRepository;
import com.mitsko.financial_system.repository.RepositoryFactory;
import com.mitsko.financial_system.service.ClientService;
import com.mitsko.financial_system.service.util.Validator;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl() {
        this.clientRepository = RepositoryFactory.getInstance().getClientRepository();
    }

    @Override
    public void add(ClientDto dto) throws ValidationException {
        if (Validator.validateAge(dto.getAge())) {
            throw new ValidationException("Wrong age");
        }
        Client client = new Client(dto.getName(), dto.getSurname(), dto.getAge(), dto.getClientType());
        clientRepository.save(client);
    }
}
