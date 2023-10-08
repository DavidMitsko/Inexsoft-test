package com.mitsko.financial_system.repository;

import com.mitsko.financial_system.domain.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    void save(Account account);

    Optional<Account> getByUuid(String uuid);

    void updateByUuid(Account account, String uuid, boolean transactional, String transactionId, boolean lastAction);

    void deleteByUuid(String uuid, boolean transactional, String transactionId, boolean lastAction);

    List<Account> getAllByClientUuid(String clientUuid);

    List<Account> getAllByBankUuid(String uuid);

}
