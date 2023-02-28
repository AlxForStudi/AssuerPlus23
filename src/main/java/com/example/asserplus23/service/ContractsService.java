package com.example.asserplus23.service;

import com.example.asserplus23.model.Contracts;
import com.example.asserplus23.repository.ContractsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractsService{
    @Autowired
    ContractsRepository contractsRepository;

    public List<Contracts> getContracts(){
        return contractsRepository.findAll();
    }
    public Contracts getContract(Long id){
        return contractsRepository.findById(id).get();
    }
    public List<Contracts> getContractsByClientsId(Long id){return contractsRepository.findContractsByclientid(id);}
    public Contracts getContractByCode(String code){return contractsRepository.findContractsByCode(code);}
    public void addContract(Contracts newEntry){
        contractsRepository.save(newEntry);
    }
    public void updateContracts( Contracts update ,Long id){}
    public void deleteContracts(Long id){}
}
