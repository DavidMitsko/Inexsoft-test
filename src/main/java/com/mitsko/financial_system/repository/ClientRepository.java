package com.mitsko.financial_system.repository;

import com.mitsko.financial_system.domain.entity.Client;

import java.util.Optional;

public interface ClientRepository {

    void save(Client client);

    Optional<Client> getByUuid(String uuid);

    void updateByUuid(Client bank, String uuid);

    void deleteByUuid(String uuid);

}
