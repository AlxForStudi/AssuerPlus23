package com.example.asserplus23.repository;


import com.example.asserplus23.model.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokensRepository extends JpaRepository<Tokens,Long> {
    public Tokens findTokensByIdentifiant(String identifiant);
}
