package com.example.asserplus23.model;

import com.example.asserplus23.service.HashService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class Tokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identifiant;
    private String validity;

    private void generateValidity(){
        this.validity = LocalDateTime.now(ZoneId.of("Europe/Paris")).plusMinutes(2).toString();
    }
    public Tokens(){}

    public Tokens(String identifiantHash){
        this.identifiant= identifiantHash;
        generateValidity();
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

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

}
