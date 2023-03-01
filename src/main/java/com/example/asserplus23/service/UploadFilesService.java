package com.example.asserplus23.service;

import com.example.asserplus23.daoService.SinistresDao;
import com.example.asserplus23.model.Sinistres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


@Service
public class UploadFilesService {

    @Autowired
    SinistresDao sinistresDao;
    @Autowired
    GeneratorService generatorService;

    /*Enregistrement fichiers*/
    public String uploadFilesTemp(ArrayList<MultipartFile> photos, Sinistres linkedSinistre, Long userId, String type){
        String UPLOAD_DIRECTORY = System.getProperty("user.dir")+"/temp/"+userId;
        try {
            for (MultipartFile file : photos) {
                String fileName = (photos.indexOf(file)+1) + generatorService.generateFileName(type,linkedSinistre,file);
                this.saveMultipartFile(file,UPLOAD_DIRECTORY,fileName);
            }
        }catch (IOException ioe){
            return "Une erreure est survenu lors de l'upload : "+type;
        }
        return "";
    }

    public void saveMultipartFile (MultipartFile file,String path,String fileName) throws IOException {
        Path fileNameAndPath = Paths.get(path, fileName);
        File dir = new File(path);
        dir.mkdir();
        Files.write(fileNameAndPath, file.getBytes());
    }

    public ArrayList<File> getTempFilesByClientsId (Long clientId){
        ArrayList<File> arrayListFiles = new ArrayList<>();
        File dir = new File(System.getProperty("user.dir")+"/temp/"+clientId);
        File[] filesList = dir.listFiles();
        for (File file : filesList){
            arrayListFiles.add(file);
        }
        return arrayListFiles;
    }
}
