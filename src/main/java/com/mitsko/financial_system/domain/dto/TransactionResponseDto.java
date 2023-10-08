package com.mitsko.financial_system.domain.dto;

import com.mitsko.financial_system.domain.dto.base.BaseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponseDto extends BaseDto {

    private LocalDateTime transactionTime;

    private BigDecimal amount;

    private BigDecimal commission;

    private ClientDto sender;

    private ClientDto recipient;

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public ClientDto getSender() {
        return sender;
    }

    public void setSender(ClientDto sender) {
        this.sender = sender;
    }

    public ClientDto getRecipient() {
        return recipient;
    }

    public void setRecipient(ClientDto recipient) {
        this.recipient = recipient;
    }
}
