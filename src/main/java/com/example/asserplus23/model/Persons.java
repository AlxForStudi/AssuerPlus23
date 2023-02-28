package com.example.asserplus23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Persons {
    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String phonenumber;
    private Long companyid;

    public Persons() {}

    public Persons(Long id, String firstname, String lastname, String address, String email, String phonenumber, Long companyid) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.phonenumber = phonenumber;
        this.companyid = companyid;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
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

    public String getPhoneNumber() {
        return phonenumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phonenumber = phoneNumber;
    }

    public Long getCompanyId() {
        return companyid;
    }

    public void setCompanyId(Long companyId) {
        this.companyid = companyId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + this.getId() +
                ", firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", address='" + this.getAddress() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", phoneNumber='" + this.getPhoneNumber() + '\'' +
                ", companyId=" + this.getCompanyId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persons persons)) return false;
        return getId().equals(persons.getId())
                && getFirstName().equals(persons.getFirstName())
                && getLastName().equals(persons.getLastName())
                && getAddress().equals(persons.getAddress())
                && getEmail().equals(persons.getEmail())
                && getPhoneNumber().equals(persons.getPhoneNumber())
                && getCompanyId().equals(persons.getCompanyId());
    }
}
