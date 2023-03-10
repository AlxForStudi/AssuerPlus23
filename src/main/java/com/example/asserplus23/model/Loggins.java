package com.example.asserplus23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Loggins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identifiant;
    private String password;
    private String salt;

    public Loggins() {}

    public Loggins(Long id, String identifiant, String password,String salt) {
        this.id = id;
        this.identifiant = identifiant;
        this.password = password;
        this.salt = salt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public String getSalt() {return salt;}

    public void setSalt(String salt) {this.salt = salt;}

    @Override
    public String toString() {
        return "Loggins{" +
                "id=" + this.getId() +
                ", identifiant='" + this.getIdentifiant() + '\'' +
                ", passWord='" + this.getPassWord() + '\'' +
                ", salt='" + this.getSalt() + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loggins loggins)) return false;
        return getId().equals(loggins.getId())
                && getIdentifiant().equals(loggins.getIdentifiant())
                && getPassWord().equals(loggins.getPassWord())
                && getSalt().equals(loggins.getSalt());
    }
}
