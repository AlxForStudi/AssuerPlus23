package com.example.asserplus23.service;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class CheckFormService {

    /** Verification du type MIME des fichiers **/
    public boolean checkFiles(ArrayList<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            String typeMime = new Tika().detect(file.getBytes());
            System.out.println(typeMime);
            if ((!typeMime.equals("image/png")) && (!typeMime.equals("image/jpg")) && (!typeMime.equals("image/jpeg")) && (!typeMime.equals("application/pdf"))){
                return false;
            }
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
