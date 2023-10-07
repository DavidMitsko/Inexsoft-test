package com.mitsko.financial_system.domain.dto.bank;

public class BankDto {

    private String name;

    private Integer individualsCommission;

    private Integer legalEntitiesCommission;

    public BankDto() {
    }

    public BankDto(String name, Integer individualsCommission, Integer legalEntitiesCommission) {
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
}
