package com.example.asserplus23;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestExecution;

import java.io.File;

@SpringBootTest
class AsserPlus23ApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void DirTest(){
        File dir = new File(System.getProperty("user.dir") + "/temp/" + "DirTest");
        dir.mkdir();
        System.out.println("DirTest");
    }
    @AfterTestExecution
    static void afterAll(){
        File dir = new File(System.getProperty("user.dir") + "/temp/" + "DirTest");
        /*Assertions.assertTrue(dir.exists());*/
        System.out.println("DirTest");
    }

}
