package com.example.asserplus23.service;

import com.example.asserplus23.exception.IncorrectFormException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class UploadService {

    public String uploadForm(ArrayList<MultipartFile> photos, ArrayList<MultipartFile> constats, String constract, String lieu, String date) throws IncorrectFormException {
        /** Vérification de la date et des fichier avant upload **/
        try {
            checkData(photos,constats,date);
        }
        catch (IOException ioe){
            throw new IncorrectFormException("Une erreure est survenu lors de la vérification");
        }

        /** Upload des fichier (pour l'instant en local (en BDD dans le futur) **/
        String UPLOAD_DIRECTORY = System.getProperty("user.dir")+"/uploads";
        try {
            for (MultipartFile file : photos) {
                StringBuilder fileNames = new StringBuilder();
                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
                fileNames.append(file.getOriginalFilename());
                Files.write(fileNameAndPath, file.getBytes());
            }
            for (MultipartFile file : constats) {
                StringBuilder fileNames = new StringBuilder();
                Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
                fileNames.append(file.getOriginalFilename());
                Files.write(fileNameAndPath, file.getBytes());
            }
        }catch (IOException ioe){
            throw new IncorrectFormException("Une erreure est survenu lors de l'upload");
        }
        return "Votre déclaration à bien été prise en compte pour votre contrat n° "+constract;
    }

    /** Fonction appelant l'outil CheckFormService pour vérife data**/
    public void checkData(ArrayList<MultipartFile> photos, ArrayList<MultipartFile> constats, String date) throws IOException, IncorrectFormException {
        CheckFormService checkFormService = new CheckFormService();
        if (checkFormService.checkFiles(photos)) {
            if (checkFormService.checkFiles(constats)){
                if (checkFormService.checkStringDate(date)){

                }else {
                    throw new IncorrectFormException("La date renseigné n'est pas correcte !");
                }
            }else {
                throw new IncorrectFormException("Le type de fichier pour constat n'est pas autorisé !");
            }
        }else {
            throw new IncorrectFormException("Le type de fichier pour photos n'est pas autorisé !");
        }
    }
}
