package com.example.asserplus23.repository;

import com.example.asserplus23.model.Persons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonsRepository extends JpaRepository<Persons,Long> {
}
