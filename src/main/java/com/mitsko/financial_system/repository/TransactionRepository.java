package com.mitsko.financial_system.repository;

import com.mitsko.financial_system.domain.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    void save(Transaction transaction, boolean transactional, String transactionId, boolean lastAction);

    Optional<Transaction> getByUuid(String uuid);

    List<Transaction> getBySenderUuidAndTransactionTimeBetween(String clientUuid, LocalDateTime startTime, LocalDateTime endTime);

    List<Transaction> getBySenderUuid(String clientUuid);

}
