package com.example.asserplus23.service;

import com.example.asserplus23.model.Files;
import com.example.asserplus23.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesService{
    @Autowired
    FilesRepository filesRepository;

    public List<Files> getFiles(){
        return filesRepository.findAll();
    }
    public Files getFile(Long id){
        return filesRepository.findById(id).get();
    }
    public void addFile(Files newEntry){
        filesRepository.save(newEntry);
    }
    public void updateFiles( Files update ,Long id){}
    public void deleteFiles(Long id){}
}