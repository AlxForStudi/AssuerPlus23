package com.example.asserplus23.service;

import com.example.asserplus23.model.Companies;
import com.example.asserplus23.repository.CompaniesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompaniesService{
    @Autowired
    CompaniesRepository companiesRepository;

    public List<Companies> getCompanies(){
        return companiesRepository.findAll();
    }
    public Companies getCompanies(Long id){
        return companiesRepository.findById(id).get();
    }
    public void addCompanies(Companies newEntry){
        companiesRepository.save(newEntry);
    }
    public void updateCompanies( Companies update ,Long id){}
    public void deleteCompanies(Long id){}
}
