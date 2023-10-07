package com.mitsko.financial_system.domain.entity.base;

import java.util.UUID;

public class BaseEntity {

    private String uuid;

    public BaseEntity() {
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
