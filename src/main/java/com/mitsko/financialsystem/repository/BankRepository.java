package com.mitsko.financialsystem.repository;

import com.mitsko.financialsystem.domain.entity.Bank;

import java.util.List;
import java.util.Optional;

public interface BankRepository {

    void save(Bank bank);

    Optional<Bank> getByUuid(String uuid);

    void updateByUuid(Bank bank, String uuid);

    void deleteByUuid(String uuid, boolean transactional, String transactionId, boolean lastAction);

    List<Bank> getAll();

}
