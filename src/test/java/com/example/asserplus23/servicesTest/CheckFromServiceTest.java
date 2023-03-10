package com.example.asserplus23.servicesTest;

import com.example.asserplus23.service.CheckFormService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckFromServiceTest {

    @Autowired
    CheckFormService testCheckFromService = new CheckFormService();
    private static String RESOURCE_DIRECTORY;
    private static MultipartFile MULTI_PNG;
    private static MultipartFile MULTI_PDF;
    private static MultipartFile MULTI_TXT;
    private static LocalDate MIN_DATE;
    private static LocalDate MAX_DATE;

    @BeforeAll
    public static void beforeAll() throws IOException {
        /*Repertoir pour récupérer les fichiers test*/
        RESOURCE_DIRECTORY = System.getProperty("user.dir") + "/src/test/java/com/example/asserplus23/testResources";
        MULTI_PNG = new MockMultipartFile("test1.png", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testPNG1.PNG")));
        MULTI_PDF = new MockMultipartFile("test1.pdf", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testPDF1.pdf")));
        MULTI_TXT = new MockMultipartFile("test3.png", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testFautFichierPNG.png")));
        MIN_DATE = LocalDate.of(2023,02,11);
        MAX_DATE = LocalDate.of(2023,02,13);
        System.out.println("before all");

    }
    @Test
    public void checkFilesTest_withFilesAccept() throws IOException {
        /*Init variables neccessaires à la fonction*/
        ArrayList<MultipartFile> filesList = new ArrayList<MultipartFile>();
        filesList.add(MULTI_PNG);
        filesList.add(MULTI_PDF);

        /*Execution fonction et Test*/
        Assertions.assertTrue(testCheckFromService.checkFiles(filesList));
        System.out.println("exec checkFilesTest_withFilesAccept");
    }
    @Test
    public void checkFilesTest_withFilesReject() throws IOException {
        /*Init variables neccessaires à la fonction*/
        ArrayList<MultipartFile> filesList = new ArrayList<MultipartFile>();
        filesList.add(MULTI_PDF);
        filesList.add(MULTI_TXT);

        /*Execution fonction et Test*/
        Assertions.assertFalse(testCheckFromService.checkFiles(filesList));
        System.out.println("exec checkFilesTest_withFilesReject");
    }

    @Test
    public void checkStringDateTest_withDateOk(){
        /*Init variables neccessaires à la fonction*/
        String testDate = "2023-02-12";

        /*Execution fonction et Test*/
        Assertions.assertTrue(testCheckFromService.checkStringDate(testDate,MIN_DATE,MAX_DATE));
        System.out.println("exec checkStringDateTest_withDateOk");
    }
    @Test
    public void checkStringDateTest_withDateSupMax(){
        /*Init variables neccessaires à la fonction*/
        String testDate = "2023-02-13";

        /*Execution fonction et Test*/
        Assertions.assertFalse(testCheckFromService.checkStringDate(testDate,MIN_DATE,MAX_DATE));
        System.out.println("exec checkStringDateTest_withDateSupMax");
    }
    @Test
    public void checkStringDateTest_withDateInfMin(){
        /*Init variables neccessaires à la fonction*/
        String testDate = "2023-02-11";

        /*Execution fonction et Test*/
        Assertions.assertFalse(testCheckFromService.checkStringDate(testDate,MIN_DATE,MAX_DATE));
        System.out.println("exec checkStringDateTest_withDateInfMin");
    }


}
