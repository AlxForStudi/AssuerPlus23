package com.example.asserplus23.daoService;

import com.example.asserplus23.model.Sinistres;
import com.example.asserplus23.repository.SinistresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinistresDao {
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

}
