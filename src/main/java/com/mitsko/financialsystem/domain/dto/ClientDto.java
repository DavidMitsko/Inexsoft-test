package com.mitsko.financialsystem.domain.dto;

import com.mitsko.financialsystem.domain.dto.base.BaseDto;
import com.mitsko.financialsystem.domain.enums.ClientType;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDto clientDto = (ClientDto) o;
        return name.equals(clientDto.name) && surname.equals(clientDto.surname) && age.equals(clientDto.age) && clientType == clientDto.clientType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, clientType);
    }
}
