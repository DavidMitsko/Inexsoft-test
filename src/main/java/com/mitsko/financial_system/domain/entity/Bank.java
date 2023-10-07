package com.mitsko.financial_system.domain.entity;

import com.mitsko.financial_system.domain.entity.base.BaseEntity;

import java.util.Objects;

public class Bank extends BaseEntity {

    private String name;

    private Integer individualsCommission;

    private Integer legalEntitiesCommission;

    public Bank(String name, Integer individualsCommission, Integer legalEntitiesCommission) {
        this.name = name;
        this.individualsCommission = individualsCommission;
        this.legalEntitiesCommission = legalEntitiesCommission;
    }

    public Bank(String uuid, String name, Integer individualsCommission, Integer legalEntitiesCommission) {
        super(uuid);
        this.name = name;
        this.individualsCommission = individualsCommission;
        this.legalEntitiesCommission = legalEntitiesCommission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndividualsCommission() {
        return individualsCommission;
    }

    public void setIndividualsCommission(Integer individualsCommission) {
        this.individualsCommission = individualsCommission;
    }

    public Integer getLegalEntitiesCommission() {
        return legalEntitiesCommission;
    }

    public void setLegalEntitiesCommission(Integer legalEntitiesCommission) {
        this.legalEntitiesCommission = legalEntitiesCommission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return name.equals(bank.name) && individualsCommission.equals(bank.individualsCommission)
                && legalEntitiesCommission.equals(bank.legalEntitiesCommission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, individualsCommission, legalEntitiesCommission);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", individualsCommission=" + individualsCommission +
                ", legalEntitiesCommission=" + legalEntitiesCommission +
                '}';
    }
}
