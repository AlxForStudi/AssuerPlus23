package com.example.asserplus23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Companies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String siret;
    private String name;
    private String address;
    private String email;
    private String phonenumber;
    private String faxnumber;

    public Companies(){}
    public Companies(Long id, String name,String siret, String address, String email, String phonenumber, String faxnumber) {
        this.id = id;
        this.name = name;
        this.siret = siret;
        this.address = address;
        this.email = email;
        this.phonenumber = phonenumber;
        this.faxnumber = faxnumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFaxnumber() {
        return faxnumber;
    }

    public void setFaxNumber(String faxnumber) {
        this.faxnumber = faxnumber;
    }

    @Override
    public String toString() {
        return "Companies{" +
                "id=" + this.getId() +
                ", siret='" + this.getSiret() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", address='" + this.getAddress() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", phoneNumber='" + this.getPhonenumber() + '\'' +
                ", faxNumber='" + this.getFaxnumber() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Companies companies)) return false;
        return getId().equals(companies.getId())
                && getSiret().equals(companies.getSiret())
                && getName().equals(companies.getName())
                && getAddress().equals(companies.getAddress())
                && getEmail().equals(companies.getEmail())
                && getPhonenumber().equals(companies.getPhonenumber())
                && getFaxnumber().equals(companies.getFaxnumber());
    }
}
