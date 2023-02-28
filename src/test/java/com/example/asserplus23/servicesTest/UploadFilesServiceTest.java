package com.example.asserplus23.servicesTest;



import com.example.asserplus23.model.Sinistres;
import com.example.asserplus23.service.UploadFilesService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


@SpringBootTest
@AutoConfigureMockMvc
public class UploadFilesServiceTest {
    @Autowired
    private UploadFilesService testUploadFilesService = new UploadFilesService();
    private static String SAVE_DIRECTORY;
    private static String RESOURCE_DIRECTORY;
    private static String TEMP_DIRECTORY;
    private static Long CLIENT_ID;
    private static String CLIENT_DIRECTORY;
    private static String FILE_TEST_NAME;
    private static MultipartFile MULTIPART_FILE;
    private static File[] DIR_TEST_List;



    @BeforeAll
    public static void beforeAll() throws IOException {
        System.out.println("before all");
        /*Repertoir pour récupérer les fichiers test*/
        RESOURCE_DIRECTORY = System.getProperty("user.dir")+"/src/test/java/com/example/asserplus23/testResources";
        /*Repertoir d'upload pour  saveMultipartFileTest*/
        SAVE_DIRECTORY = System.getProperty("user.dir") + "/temp/" + "TEST_saveMultipartFileTest_WhenDirExist";
        /*Données pour getTempFilesByClientsIdTest*/
        CLIENT_ID =123456L;
        CLIENT_DIRECTORY = System.getProperty("user.dir")+"/temp/"+CLIENT_ID;
        /*Nom fichier test créé */
        FILE_TEST_NAME = "TEST_TEST.png";
        MULTIPART_FILE = new MockMultipartFile("test1.pdf","test1.pdf","application/pdf", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testPDF1.pdf")));

        /*Test si les tests suivants ne supprimment pas d'autres dossiers que ceux créés pour les tests*/
        System.out.println("DirTest");
        TEMP_DIRECTORY = System.getProperty("user.dir") + "/temp/";
        File dir = new File(TEMP_DIRECTORY);
        DIR_TEST_List = dir.listFiles();
    }


    @Test
    public void saveMultipartFileTest_WhenDirNotExist() throws IOException {
        /*Supression répertoire si existe du repertoir*/
        File dir = new File(SAVE_DIRECTORY);
        dir.mkdir();
        File[] filesList = dir.listFiles();
        for (File file : filesList){
            file.delete();
        }
        if (dir.delete()){
            System.out.println("SAVE_DIRECTORY deleted");
        }else {
            System.out.println("SAVE_DIRECTORY NOT deleted !!");
        }

        /*Execution fonction*/
        testUploadFilesService.saveMultipartFile(MULTIPART_FILE,SAVE_DIRECTORY,FILE_TEST_NAME);

        /*Récupération des fichier dans repertoir et test*/
        filesList = dir.listFiles();
        Assertions.assertEquals(FILE_TEST_NAME,filesList[0].getName());
        System.out.println("exec saveMultipartFileTest_WhenDirNotExist");
    }



    @Test
    public void saveMultipartFileTest_WhenDirExist() throws IOException {
        /*Création du repertoir*/
        File dir = new File(SAVE_DIRECTORY);
        dir.mkdir();

        /*Execution fonction*/
        testUploadFilesService.saveMultipartFile(MULTIPART_FILE,SAVE_DIRECTORY,FILE_TEST_NAME);

        /*Récupération des fichier dans repertoir et test*/
        File[] filesList = dir.listFiles();
        Assertions.assertEquals(FILE_TEST_NAME,filesList[0].getName());
        System.out.println("exec saveMultipartFileTest_WhenDirExist");
    }

    @Test
    public void getTempFilesByClientsIdTest() throws IOException {
        /*Création du repertoir et du fichier a récupérer*/
        File dir = new File(CLIENT_DIRECTORY);
        dir.mkdir();
        Path fileNameAndPath = Paths.get(CLIENT_DIRECTORY, FILE_TEST_NAME);
        Files.write(fileNameAndPath,MULTIPART_FILE.getBytes());

        /*Execution fonction et test*/
        Assertions.assertEquals(FILE_TEST_NAME,testUploadFilesService.getTempFilesByClientsId(CLIENT_ID).get(0).getName());
        System.out.println("exec getTempFilesByClientsIdTest");
    }

    @Test
    public void uploadFilesTempTest() throws IOException {
        /*Init variables neccessaires à la fonction*/
        ArrayList<MultipartFile> filesList = new ArrayList<>();
        filesList.add(MULTIPART_FILE);
        Sinistres linkedSinistre = new Sinistres();
        linkedSinistre.setCode("AVP123456D");
        String type = "Test";

        /*Execution fonction et test*/
        Assertions.assertEquals("",testUploadFilesService.uploadFilesTemp(filesList,linkedSinistre,CLIENT_ID,type));
        System.out.println("exec uploadFilesTempTest");
    }

    @AfterEach
    public void afterEach(){
        System.out.println("AfterEach");
        File dir = new File(SAVE_DIRECTORY);
        dir.mkdir();
        File[] filesList = dir.listFiles();
        for (File file : filesList){
            file.delete();
        }
        if (dir.delete()){
            System.out.println("SAVE_DIRECTORY deleted");
        }else {
            System.out.println("SAVE_DIRECTORY NOT deleted !!");
        }
        dir.delete();
        dir = new File(CLIENT_DIRECTORY);
        dir.mkdir();
        filesList = dir.listFiles();
        for (File file : filesList){
            file.delete();
        }
        if (dir.delete()){
            System.out.println("CLIENT_DIRECTORY deleted");
        }else {
            System.out.println("CLIENT_DIRECTORY NOT deleted !!");
        }
        dir.delete();
    }
    @AfterAll
    public static void afterAll(){
        System.out.println("after all");
        File dir = new File(TEMP_DIRECTORY);
        File[] afterFileList = dir.listFiles();
        Assertions.assertEquals(Arrays.stream(afterFileList).count(), Arrays.stream(DIR_TEST_List).count());
        System.out.println("DirTest");
    }

}
