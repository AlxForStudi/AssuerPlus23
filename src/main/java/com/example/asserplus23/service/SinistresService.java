package com.example.asserplus23.service;

import com.example.asserplus23.model.Contracts;
import com.example.asserplus23.model.Sinistres;
import com.example.asserplus23.repository.SinistresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SinistresService{
    @Autowired
    SinistresRepository sinistResrepository;

    public List<Sinistres> getSinistres(){
        return sinistResrepository.findAll();
    }
    public Sinistres getSinistre(Long id){
        return sinistResrepository.findById(id).get();
    }
    public Sinistres getSinistreByCode(String code){return sinistResrepository.findSinistresByCode(code);}
    public void addSinistre(Sinistres newEntry){
        sinistResrepository.save(newEntry);
    }
    public void updateSinistres( Sinistres update ,Long id){}
    public void deleteSinistres(Long id){}

   /* public Sinistres constructSinistreByForm(String contractId, String code, String place, String date){
        Sinistres newSinitre = new Sinistres()
    }*/
}
