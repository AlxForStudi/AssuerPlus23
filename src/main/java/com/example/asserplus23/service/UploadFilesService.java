package com.example.asserplus23.service;

import com.example.asserplus23.daoService.SinistresDao;
import com.example.asserplus23.model.Sinistres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
        String TEMP_DIRECTORY = System.getProperty("user.dir")+"/temp/"+userId;
        try {
            for (MultipartFile file : photos) {
                String fileName = generatorService.generateFileName(type,linkedSinistre,file);
                this.saveMultipartFile(file,TEMP_DIRECTORY,fileName);
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

    public String copyFilesToUploads (ArrayList<File> files, Long clientId){
        String UPLOADS_DIRECTORY = System.getProperty("user.dir")+"/uploads/"+clientId;
        File dest = new File(UPLOADS_DIRECTORY);
        dest.mkdir();
        try {
            for (File file : files) {
                File destF = new File(UPLOADS_DIRECTORY,file.getName());
                this.copyFiles(file,destF);
            }
        }catch (IOException ioe){
                return "Une erreure est survenu lors de la copy";
            }
            this.delteTempFiles(files,clientId);
            return "";
    }

    public void copyFiles (File src, File dest) throws IOException {
        InputStream in = new FileInputStream(src);
        OutputStream out = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while((length = in.read(buffer))>0){
            out.write(buffer,0,length);
        }
        in.close();
        out.close();
    }

    public void delteTempFiles (ArrayList<File> files, Long clientId){
        for (File file : files){
            if (file.exists()){
                file.delete();
            }
        }
        File dir = new File(System.getProperty("user.dir")+"/temp/"+clientId);
        if (dir.exists()){
            dir.delete();
        }

    }
}
