package com.mitsko.financialsystem.service;

import com.mitsko.financialsystem.domain.dto.ClientDto;
import com.mitsko.financialsystem.exception.ValidationException;

import java.util.List;

public interface ClientService {

    void add(ClientDto dto) throws ValidationException;

    void delete(String clientUuid);

    List<ClientDto> getAllClients();

    void updateClient(ClientDto dto, String clientUuid);

}
