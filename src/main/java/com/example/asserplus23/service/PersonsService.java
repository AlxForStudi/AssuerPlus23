package com.example.asserplus23.service;

import com.example.asserplus23.model.Persons;
import com.example.asserplus23.repository.PersonsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonsService{
    @Autowired
    PersonsRepository personsRepository;

    public List<Persons> getPersons(){
        return personsRepository.findAll();
    }
    public Persons getPerson(Long id){
        return personsRepository.findById(id).get();
    }
    public void addPerson(Persons newEntry){
        personsRepository.save(newEntry);
    }
    public void updatePersons( Persons update ,Long id){}
    public void deletePersons(Long id){}
}
