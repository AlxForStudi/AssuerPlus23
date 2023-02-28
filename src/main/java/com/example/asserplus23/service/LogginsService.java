package com.example.asserplus23.service;

import com.example.asserplus23.model.Loggins;
import com.example.asserplus23.repository.LogginsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogginsService{
    @Autowired
    LogginsRepository logginsRepository;

    public List<Loggins> getLoggins(){
        return logginsRepository.findAll();
    }
    public Loggins getLoggin(Long id){
        return logginsRepository.findById(id).get();
    }
    public void addLoggin(Loggins newEntry){
        logginsRepository.save(newEntry);
    }
    public void updateLoggins( Loggins update ,Long id){}
    public void deleteLoggins(Long id){}
}
