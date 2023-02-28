package com.example.asserplus23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Contracts {
    @Id
    private Long id;
    private Long clientid;
    private String name;
    private String code;

    public Contracts(){}

    public Contracts(Long id, Long clientid, String name, String code) {
        this.id = id;
        this.clientid = clientid;
        this.name = name;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getclientid() {
        return clientid;
    }

    public void setclientid(Long clientid) {
        this.clientid = clientid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + this.getId() +
                ", clientId=" + this.getclientid() +
                ", name='" + this.getName() + '\'' +
                ", code='" + this.getCode()+ '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contracts contracts)) return false;
        return getId().equals(contracts.getId())
                && getclientid().equals(contracts.getclientid())
                && getName().equals(contracts.getName())
                && getCode().equals(contracts.getCode());
    }
}
