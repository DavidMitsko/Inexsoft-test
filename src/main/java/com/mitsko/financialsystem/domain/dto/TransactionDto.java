package com.mitsko.financialsystem.domain.dto;

import com.mitsko.financialsystem.domain.dto.base.BaseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransactionDto extends BaseDto {

    private LocalDateTime transactionTime;

    private String senderAccountUuid;

    private String recipientAccountUuid;

    private BigDecimal amount;

    private BigDecimal commission;

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getSenderAccountUuid() {
        return senderAccountUuid;
    }

    public void setSenderAccountUuid(String senderAccountUuid) {
        this.senderAccountUuid = senderAccountUuid;
    }

    public String getRecipientAccountUuid() {
        return recipientAccountUuid;
    }

    public void setRecipientAccountUuid(String recipientAccountUuid) {
        this.recipientAccountUuid = recipientAccountUuid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDto that = (TransactionDto) o;
        return transactionTime.equals(that.transactionTime) && senderAccountUuid.equals(that.senderAccountUuid) && recipientAccountUuid.equals(that.recipientAccountUuid) && amount.equals(that.amount) && commission.equals(that.commission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionTime, senderAccountUuid, recipientAccountUuid, amount, commission);
    }
}
