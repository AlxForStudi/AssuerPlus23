package com.example.asserplus23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Clients {
    @Id
    private Long id;
    private String usernumber;
    private Long logid;
    private Long personid;
    public Clients(){}
    public Clients(Long id, String usernumber, Long logid, Long personid) {
        this.id = id;
        this.usernumber = usernumber;
        this.logid = logid;
        this.personid = personid;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    public Long getLogid() {
        return logid;
    }

    public void setLogid(Long logid) {
        this.logid = logid;
    }

    public Long getPersonid() {
        return personid;
    }

    public void setPersonid(Long personid) {
        this.personid = personid;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + this.getId() +
                ", userNumber='" + this.getUsernumber() + '\'' +
                ", logId=" + this.getLogid() +
                ", personId=" + this.getPersonid() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clients clients)) return false;
        return getId().equals(clients.getId())
                && getUsernumber().equals(clients.getUsernumber())
                && getLogid().equals(clients.getLogid())
                && getPersonid().equals(clients.getPersonid());
    }
}
