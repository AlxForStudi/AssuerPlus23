package com.example.asserplus23.service;

import com.example.asserplus23.daoService.ContractsDao;
import com.example.asserplus23.daoService.SinistresDao;
import com.example.asserplus23.model.Contracts;
import com.example.asserplus23.model.Files;
import com.example.asserplus23.model.Sinistres;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class GeneratorService {

    @Autowired
    ContractsDao contractsDao;

    @Autowired
    SinistresDao sinistresDao;

    private static Random INDEX_SINISTRES = new Random();
    public String generateSinistreCode(Contracts contract, String date){
        return "A" +
                contract.getCode() +
                date.replaceAll("-","").substring(2) +
                (char)(INDEX_SINISTRES.nextInt(26)+97) +
                (char)(INDEX_SINISTRES.nextInt(26)+97);

    }
    public Sinistres generateSinistre (Contracts contract, String date, String lieu){
        String code = this.generateSinistreCode(contract,date);
        return new Sinistres(contract.getId(), code, lieu, date,"En cours");
    }

    public String generateFileName (String type, Sinistres linkedSinistre, MultipartFile file){
        String fileName = linkedSinistre.getCode() + type + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        return fileName;
    }

    public Files generateFile (File file){
        try{
            String name = file.getName();
            String mimetype = new Tika().detect(file);
            String link = file.getPath();
            Files newFile = new Files(name,mimetype,link);
            return newFile;
        }catch (IOException ioe){
            return null;
        }
    }

    public ArrayList<Files> generateFilesList (ArrayList<File> files){
        ArrayList<Files> newFiles = new ArrayList<>();
        for (File file : files){
            newFiles.add(this.generateFile(file));
        }
        return newFiles;
    }

    public ArrayList<Map> generateSinistresDetails (Long clientId){

        ArrayList<Map> sinistresDetails = new ArrayList<>();
        List<Contracts> clientContracts = contractsDao.getContractsByClientsId(clientId);
        for (Contracts contract : clientContracts){
            if (sinistresDao.getSinistresByContractId(contract.getId())!= null){
                List<Sinistres> contractSinistres = sinistresDao.getSinistresByContractId(contract.getId());
                for (Sinistres sinistre : contractSinistres){
                    Map<String,String> sinistreDetails = new HashMap<>();
                    sinistreDetails.put("number", sinistre.getCode());
                    sinistreDetails.put("contract", contract.getName());
                    sinistreDetails.put("date", sinistre.getDate());
                    sinistreDetails.put("place", sinistre.getPlace());
                    sinistreDetails.put("status", sinistre.getStatus());
                    sinistresDetails.add(sinistreDetails);
                }
            }
        }
        return sinistresDetails;
    }
}
