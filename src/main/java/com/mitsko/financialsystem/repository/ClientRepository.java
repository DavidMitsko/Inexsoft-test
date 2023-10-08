package com.mitsko.financialsystem.repository;

import com.mitsko.financialsystem.domain.entity.Client;

import java.util.Optional;

public interface ClientRepository {

    void save(Client client);

    Optional<Client> getByUuid(String uuid);

    void updateByUuid(Client bank, String uuid);

    void deleteByUuid(String uuid, boolean transactional, String transactionId, boolean lastAction);

}
