package com.mitsko.financialsystem.service;

import com.mitsko.financialsystem.domain.dto.ClientDto;
import com.mitsko.financialsystem.exception.ValidationException;

public interface ClientService {

    void add(ClientDto dto) throws ValidationException;

    void delete(String clientUuid);

}
