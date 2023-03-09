package com.example.asserplus23.servicesTest;



import com.example.asserplus23.model.Sinistres;
import com.example.asserplus23.service.UploadFilesService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


@SpringBootTest
@AutoConfigureMockMvc
public class UploadFilesDaoTest {
    @Autowired
    private UploadFilesService testUploadFilesService = new UploadFilesService();
    private static String SAVE_DIRECTORY;
    private static String RESOURCE_DIRECTORY;
    private static String TEMP_DIRECTORY;
    private static String UPLOADS_DIRECTORY_TEST;
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
        /*Repertoir de test dans /temp*/
        SAVE_DIRECTORY = System.getProperty("user.dir") + "/temp/" + "TEST";
        /*Repertoir definitif des fichiers*/
        UPLOADS_DIRECTORY_TEST = System.getProperty("user.dir") + "/uploads/0";
        /*Données pour getTempFilesByClientsIdTest*/
        CLIENT_ID =0L;
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

    @Test
    public  void copyFilesTest() throws IOException {
        /*Création repertoire destination*/
        File dest = new File(SAVE_DIRECTORY);
        dest.mkdir();

        /*Init variables neccessaires à la fonction*/
        File srcF = new File(RESOURCE_DIRECTORY+"/testPDF1.pdf");
        File destF = new File(SAVE_DIRECTORY,srcF.getName());

        /*Execution fonction*/
        testUploadFilesService.copyFiles(srcF,destF);

        /*Tests si fichiers identiques*/
        Assertions.assertEquals(srcF.getName(),destF.getName());
        Assertions.assertEquals(srcF.length(),destF.length());
        byte[] srcBytes = Files.readAllBytes(srcF.toPath());
        byte[] destBytes = Files.readAllBytes(destF.toPath());
        Assertions.assertTrue(Arrays.equals(srcBytes,destBytes));

    }

    @Test
    public void copyFilesToUploadsTest() throws IOException {

        /*Copie des fichiers TestResource vers /Temp*/
        File dest = new File(SAVE_DIRECTORY);
        dest.mkdir();
        File srcF1 = new File(RESOURCE_DIRECTORY+"/testPDF1.pdf");
        File destF1 = new File(SAVE_DIRECTORY,srcF1.getName());
        File srcF2 = new File(RESOURCE_DIRECTORY+"/testPNG1.PNG");
        File destF2 = new File(SAVE_DIRECTORY,srcF2.getName());
        testUploadFilesService.copyFiles(srcF1,destF1);
        testUploadFilesService.copyFiles(srcF2,destF2);


        /*Init variables neccessaires à la fonction*/
        ArrayList<File> files = new ArrayList<>();
        files.add(destF2);
        files.add(destF1);

        /*Execution fonction*/
        testUploadFilesService.copyFilesToUploads(files,CLIENT_ID);

        /*Lecture du resultat de la fonction*/
        File uploadsFile = new File(UPLOADS_DIRECTORY_TEST);
        ArrayList<File> filesFind = new ArrayList<>();
        System.out.println(uploadsFile.getPath());
        System.out.println(uploadsFile.listFiles());
        for (File f : uploadsFile.listFiles()) {
            filesFind.add(f);
            }

        /*Résultat attendu par la fonction*/
        ArrayList<File> filesToFind = new ArrayList<>();
        filesToFind.add(new File(UPLOADS_DIRECTORY_TEST+"/testPDF1.pdf"));
        filesToFind.add(new File(UPLOADS_DIRECTORY_TEST+"/testPNG1.PNG"));

        /*Test*/
        Assertions.assertTrue(filesFind.equals(filesToFind));
    }

    @AfterEach
    public void afterEach(){
        /*Supression SAVE_DIRECTORY*/
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
        /*Supression CLIENT_DIRECTORY*/
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
        /*Supression UPLOADS_DIRECTORY_TEST */
        dir = new File(UPLOADS_DIRECTORY_TEST);
        dir.mkdir();
        filesList = dir.listFiles();
        for (File file : filesList){
            file.delete();
        }
        if (dir.delete()){
            System.out.println("UPLOADS_DIRECTORY deleted");
        }else {
            System.out.println("UPLOADS_DIRECTORY NOT deleted !!");
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
