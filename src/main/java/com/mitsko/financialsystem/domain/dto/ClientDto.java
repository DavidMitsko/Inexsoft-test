package com.mitsko.financialsystem.domain.dto;

import com.mitsko.financialsystem.domain.dto.base.BaseDto;
import com.mitsko.financialsystem.domain.enums.ClientType;

public class ClientDto extends BaseDto {

    private String name;

    private String surname;

    private Integer age;

    private ClientType clientType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}
