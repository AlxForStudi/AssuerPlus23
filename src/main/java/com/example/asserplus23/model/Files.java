package com.example.asserplus23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Files {
    @Id
    private Long id;
    private String name;
    private String mimetype;
    private String link;
    private Long sinistreid;

    public Files() {}

    public Files(Long id, String name, String mimetype, String link, Long sinistreid) {
        this.id = id;
        this.name = name;
        this.mimetype = mimetype;
        this.link = link;
        this.sinistreid = sinistreid;
    }

    public Files(String name, String mimeType, String link) {
        this.name = name;
        this.mimetype = mimeType;
        this.link = link;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimeType(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getSinistreid() {
        return sinistreid;
    }

    public void setSinistreid(Long sinistreid) {
        this.sinistreid = sinistreid;
    }

    @Override
    public String toString() {
        return "Files{" +
                "Id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", mimeType='" + this.getMimetype() + '\'' +
                ", link='" + this.getLink() + '\'' +
                ", sinistreId=" + this.getSinistreid() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Files files)) return false;
        return getId().equals(files.getId())
                && getName().equals(files.getName())
                && getMimetype().equals(files.getMimetype())
                && getLink().equals(files.getLink())
                && getSinistreid().equals(files.getSinistreid());
    }
}
