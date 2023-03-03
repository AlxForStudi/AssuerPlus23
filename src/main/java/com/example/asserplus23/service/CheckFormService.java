package com.example.asserplus23.service;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class CheckFormService {
    /** Fonction appelant l'outil CheckFormService pour vérife data**/
    public String checkData(ArrayList<MultipartFile> photos, ArrayList<MultipartFile> constats, String date){
        if (this.checkStringDate(date)) {
            if (this.checkFiles(photos)) {
                if (this.checkFiles(constats)) {
                    return "";
                } else {
                    return "Le type de fichier pour constat n'est pas autorisé !";
                }
            } else {
                return "Le type de fichier pour photos n'est pas autorisé !";
            }
        }else {
                return "La date renseigné n'est pas correcte !";
        }
    }

    /** Verification du type MIME des fichiers **/
    public boolean checkFiles(ArrayList<MultipartFile> files){
        try{
            for (MultipartFile file : files) {
                String typeMime = new Tika().detect(file.getBytes());
                if ((!typeMime.equals("image/png")) && (!typeMime.equals("image/jpg")) && (!typeMime.equals("image/jpeg")) && (!typeMime.equals("application/pdf"))) {
                    return false;
                }
            }
        }catch (IOException ioe) {
            return false;
        }
        return true;
    }

    /**Verification date comprise entre max et min (max est min passés en paramètre)**/
    public Boolean checkStringDate(String dateToCheckString, LocalDate dateMinValid, LocalDate dateMaxValid) {
        LocalDate dateToCheckLDT = LocalDate.parse(dateToCheckString);
        if ((dateMinValid.isBefore(dateToCheckLDT)) && (dateMaxValid.isAfter(dateToCheckLDT))) {
            return true;
        } else {
            return false;
        }
    }

    /**Verification date comprise entre max et min (min passés en paramètre et max = demain)**/
    public Boolean checkStringDate(String dateToCheckString, LocalDate dateMinValid) {
        if (dateMinValid == null) {
            dateMinValid = LocalDate.now(ZoneId.of("Europe/Paris")).with(LocalTime.of(0, 1));
        };
        LocalDate dateMaxValid = LocalDate.now(ZoneId.of("Europe/Paris"));
        LocalDate dateToCheckLDT = LocalDate.parse(dateToCheckString);
        if ((dateMinValid.isBefore(dateToCheckLDT)) && (dateMaxValid.isAfter(dateToCheckLDT))) {
            return true;
        } else {
            return false;
        }
    }

    /**Verification date comprise entre max et min (min = avant hier et max = demain)**/
    public Boolean checkStringDate(String dateToCheckString) {
        LocalDate dateMinValid = LocalDate.now(ZoneId.of("Europe/Paris")).minusDays(2);
        LocalDate dateMaxValid = LocalDate.now(ZoneId.of("Europe/Paris")).plusDays(1);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateToCheckLDT = LocalDate.parse(dateToCheckString,format);
        if ((dateMinValid.isBefore(dateToCheckLDT)) && (dateMaxValid.isAfter(dateToCheckLDT))) {
            return true;
        } else {
            return false;
        }
    }

}
