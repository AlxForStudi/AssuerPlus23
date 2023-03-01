package com.example.asserplus23.servicesTest;

import com.example.asserplus23.model.Contracts;
import com.example.asserplus23.model.Files;
import com.example.asserplus23.model.Sinistres;
import com.example.asserplus23.service.GeneratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class GeneratorServiceTest {
    @Autowired
    GeneratorService testGeneratorService = new GeneratorService();

    private static String RESOURCE_DIRECTORY;
    @BeforeAll
    public static void beforeAll() {
        /*Repertoir pour récupérer les fichiers test*/
        RESOURCE_DIRECTORY = System.getProperty("user.dir") + "/src/test/java/com/example/asserplus23/testResources";
        System.out.println("before all");
    }
    @Test
    public void generateSinistreCodeTest(){
        /*Init variables neccessaires à la fonction*/
        Contracts contract = new Contracts();
        contract.setCode("VP123456D");
        String date = "2023-02-23";

        /*Execution fonction*/
        String testCode = testGeneratorService.generateSinistreCode(contract,date);

        /*Test*/
        Assertions.assertTrue(testCode.contains("VP123456D230223"));
        System.out.println("exec generateSinistreCodeTest");
    }

    @Test
    public void generateSinistreTest() {
        /*Init variables neccessaires à la fonction*/
        Contracts contract = new Contracts();
        contract.setId(1L);
        contract.setCode("VP123456D");
        String date = "2023-02-23";
        String lieu = "nulpart";

        /*Execution fonction*/
        Sinistres newSinistre = testGeneratorService.generateSinistre(contract, date, lieu);

        /*Tests*/
        Assertions.assertEquals("nulpart", newSinistre.getPlace());
        Assertions.assertEquals(contract.getId(), newSinistre.getContractId());
        Assertions.assertTrue(newSinistre.getCode().contains("VP123456D230223"));
        Assertions.assertEquals("2023-02-23", newSinistre.getDate());
        System.out.println("exec generateSinistreTest");
    }


    @Test
    public void generateFileNameTest () throws IOException {
        /*Init variables neccessaires à la fonction*/
        String type = "constat";
        Sinistres linkedSinistre = new Sinistres();
        linkedSinistre.setCode("AVP123456D");
        MultipartFile file = new MockMultipartFile("test1.pdf","test1.pdf","application/pdf", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testPDF1.pdf")));

        /*Execution fonction*/
        testGeneratorService.generateFileName(type,linkedSinistre,file);

        /*Test*/
        Assertions.assertEquals("AVP123456Dconstat.pdf",testGeneratorService.generateFileName(type,linkedSinistre,file));
        System.out.println("exec generateFileNameTest");
    }

    @Test
    public void generateFileTest(){
        /*Init variables neccessaires à la fonction*/
        File file = new File(RESOURCE_DIRECTORY+"/testPDF1.pdf");

        /*Execution fonction*/
        Files newFile = testGeneratorService.generateFile(file);

        /*Tests*/
        Assertions.assertEquals("testPDF1.pdf",newFile.getName());
        Assertions.assertEquals("application/pdf",newFile.getMimetype());
        Assertions.assertTrue(newFile.getLink().contains("src\\test\\java\\com\\example\\asserplus23\\testResources\\testPDF1.pdf"));
        System.out.println("exec generateFileTest");
    }

    @Test
    public void generateFilesListTest(){
        /*Init variables neccessaires à la fonction*/
        ArrayList<File> filesList = new ArrayList<>();
        filesList.add(new File(RESOURCE_DIRECTORY+"/testPDF1.pdf"));
        filesList.add(new File(RESOURCE_DIRECTORY+"/testPNG1.png"));

        /*Execution fonction*/
        ArrayList<Files> newFilesList = testGeneratorService.generateFilesList(filesList);

        /*Tests*/
        for (Files file : newFilesList){
            Assertions.assertTrue(file.getName().contains("testP"));
            Assertions.assertTrue(file.getMimetype()!="");
            Assertions.assertTrue(file.getLink().contains("src\\test\\java\\com\\example\\asserplus23\\testResources\\testP"));
            System.out.println("exec generateFilesListTest");
        }
    }
}
