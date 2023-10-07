package com.mitsko.financial_system.domain.entity;

import com.mitsko.financial_system.domain.entity.base.BaseEntity;
import com.mitsko.financial_system.domain.enums.ClientType;

import java.util.Objects;

public class Client extends BaseEntity {

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
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", clientType=" + clientType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return name.equals(client.name) && surname.equals(client.surname) && Objects.equals(age, client.age) && clientType == client.clientType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, clientType);
    }

}
