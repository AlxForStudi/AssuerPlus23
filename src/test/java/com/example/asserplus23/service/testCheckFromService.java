package com.example.asserplus23.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
@RunWith(MockitoJUnitRunner.class)
public class testCheckFromService {

    @InjectMocks
    CheckFormService testCheckFromService = new CheckFormService();

    @Test
    public void checkFilesTest() throws IOException {

        /*Création des fichiers en Mock via fichiers dans répertoire testResources*/
        ArrayList<MultipartFile> files = new ArrayList<MultipartFile>();
        String RESOURCE_DIRECTORY = System.getProperty("user.dir")+"/src/test/java/com/example/asserplus23/testResources";
        MultipartFile multiPNG1 = new MockMultipartFile("test1.png", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testPNG1.PNG")));
        MultipartFile multiPDF1 = new MockMultipartFile("test1.pdf", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testPDF1.pdf")));
        MultipartFile multiTXT1 = new MockMultipartFile("test3.png", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testFautFichierPNG.png")));

        /*Test pour fichiers multiple avec MIME accepté */
        files.add(multiPNG1);
        files.add(multiPDF1);
        Assert.assertTrue(testCheckFromService.checkFiles(files));

        /*Test pour fichiers multiple dont 1 avec MIME rejeté */
        files.add(multiTXT1);
        Assert.assertFalse(testCheckFromService.checkFiles(files));
    }

    @Test
    public void checkStringDateTest(){
        LocalDate minDate = LocalDate.of(2023,02,11);
        LocalDate maxDate = LocalDate.of(2023,02,13);

        /*Test pour : min < date < max */
        String testDate = "2023-02-12";
        Assert.assertTrue(testCheckFromService.checkStringDate(testDate,minDate,maxDate));

        /*Test pour : date => max */
        testDate = "2023-02-13";
        Assert.assertFalse(testCheckFromService.checkStringDate(testDate,minDate,maxDate));

        /*Test pour : date <= min */
        testDate = "2023-02-11";
        Assert.assertFalse(testCheckFromService.checkStringDate(testDate,minDate,maxDate));
    }


}
