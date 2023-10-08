package com.mitsko.financialsystem.domain.dto;

import com.mitsko.financialsystem.domain.dto.base.BaseDto;

import java.util.Objects;

public class BankDto extends BaseDto {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankDto bankDto = (BankDto) o;
        return name.equals(bankDto.name) && individualsCommission.equals(bankDto.individualsCommission) && legalEntitiesCommission.equals(bankDto.legalEntitiesCommission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, individualsCommission, legalEntitiesCommission);
    }
}
