package com.example.asserplus23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Entity
public class Sinistres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long contractid;
    private String code;
    private String place;
    private String date;

    public Sinistres() {}

    public Sinistres(Long id, Long contractid, String code, String place, String date) {
        this.id = id;
        this.contractid = contractid;
        this.code = code;
        this.place = place;
        this.date = date;
    }

    public Sinistres(Long contractId, String code, String place, String date) {
        this.contractid = contractId;
        this.code = code;
        this.place = place;
        this.date = date;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractid;
    }

    public void setContractId(Long contractId) {
        this.contractid = contractId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Sinistres{" +
                "id=" + this.getId() +
                ", contractId=" + this.getContractId() +
                ", code='" + this.getCode() + '\'' +
                ", place='" + this.getPlace() + '\'' +
                ", date='" + this.getDate() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sinistres sinistres)) return false;
        return getId().equals(sinistres.getId())
                && getContractId().equals(sinistres.getContractId())
                && getCode().equals(sinistres.getCode())
                && getPlace().equals(sinistres.getPlace())
                && getDate().equals(sinistres.getDate());
    }

}
