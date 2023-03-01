package com.example.asserplus23.repository;

import com.example.asserplus23.model.Sinistres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinistresRepository extends JpaRepository<Sinistres,Long> {
    public Sinistres findSinistresByCode(String code);
}
