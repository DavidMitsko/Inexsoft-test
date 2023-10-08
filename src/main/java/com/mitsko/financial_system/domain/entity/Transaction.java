package com.mitsko.financial_system.domain.entity;

import com.mitsko.financial_system.domain.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction extends BaseEntity {

    private LocalDateTime transactionTime;

    private String senderAccountUuid;

    private String recipientAccountUuid;

    private BigDecimal amount;

    private BigDecimal commission;

    private Transaction() {
    }

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
        Transaction that = (Transaction) o;
        return transactionTime.equals(that.transactionTime) && senderAccountUuid.equals(that.senderAccountUuid) && recipientAccountUuid.equals(that.recipientAccountUuid) && amount.equals(that.amount) && commission.equals(that.commission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionTime, senderAccountUuid, recipientAccountUuid, amount, commission);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "time=" + transactionTime +
                ", senderAccountUuid='" + senderAccountUuid + '\'' +
                ", recipientAccountUuid='" + recipientAccountUuid + '\'' +
                ", amount=" + amount +
                ", commission=" + commission +
                '}';
    }

    public static Builder builder() {
        return new Transaction().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setUuid(String uuid) {
            Transaction.this.setUuid(uuid);
            return this;
        }

        public Builder setTransactionTime(LocalDateTime time) {
            Transaction.this.transactionTime = time;
            return this;
        }

        public Builder setSenderAccountUuid(String senderAccountUuid) {
            Transaction.this.senderAccountUuid = senderAccountUuid;
            return this;
        }

        public Builder setRecipientAccountUuid(String recipientAccountUuid) {
            Transaction.this.recipientAccountUuid = recipientAccountUuid;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            Transaction.this.amount = amount;
            return this;
        }

        public Builder setCommission(BigDecimal commission) {
            Transaction.this.commission = commission;
            return this;
        }

        public Transaction build() {
            return Transaction.this;
        }
    }

}
