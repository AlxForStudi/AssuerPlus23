package com.example.asserplus23.repository;

import com.example.asserplus23.model.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractsRepository extends JpaRepository<Contracts,Long> {
    public List<Contracts> findContractsByclientid(Long clientid);
    public Contracts findContractsByCode(String code);
}
