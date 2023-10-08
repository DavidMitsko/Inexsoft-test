package com.mitsko.financial_system.service;

import com.mitsko.financial_system.domain.dto.ClientDto;
import com.mitsko.financial_system.exception.ValidationException;

public interface ClientService {

    void add(ClientDto dto) throws ValidationException;

    void delete(String clientUuid);

}
