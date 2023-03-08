package com.example.asserplus23.repository;

import com.example.asserplus23.model.Sinistres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SinistresRepository extends JpaRepository<Sinistres,Long> {
    public Sinistres findSinistresByCode(String code);
    public List<Sinistres> findAllByContractid(Long contractId);
}
