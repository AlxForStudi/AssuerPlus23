package com.example.asserplus23.servicesTest;

import com.example.asserplus23.service.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginServiceTest {

    @Autowired
    private LoginService loginService = new LoginService();

    private static String JOHN_IDENTIFIANT;
    private static String JOHN_PASSWORD;
    private static String FAKE_IDENTIFIANT;
    private static String FAKE_PASSWORD;
    private static String FAKE_TOKEN;
    @BeforeAll
    public static void beforeAll(){
        JOHN_IDENTIFIANT = "johndupont44";
        JOHN_PASSWORD = "JohnDupont1234%";
        FAKE_IDENTIFIANT = "FAKE_IDENTIFIANT";
        FAKE_PASSWORD = "FAKE_PASSWORD";
        FAKE_TOKEN = "3ed7910775090e4567ce53fd6e03da6e92d68282ea993a619852e7b2e4de2870";
    }

    @Test
    public void checkConnexionTest_withGoodLogin(){
        Assertions.assertEquals(64,loginService.getToken(JOHN_IDENTIFIANT,JOHN_PASSWORD).length());
    }
    @Test
    public void checkConnexionTest_withGoodIdentifiant_andWrongPassword(){

        Assertions.assertEquals(null,loginService.getToken(JOHN_IDENTIFIANT,FAKE_PASSWORD));
    }
    @Test
    public void checkConnexionTest_withWrongIdentifiant(){

        Assertions.assertEquals(null,loginService.getToken(FAKE_IDENTIFIANT,JOHN_PASSWORD));
    }
    @Test
    public void IsValidTokenTest_withValidToken(){
        String tokenId = loginService.getToken(JOHN_IDENTIFIANT,JOHN_PASSWORD);
        Assertions.assertTrue(loginService.isValidToken(tokenId));
    }
    @Test
    public void IsValidTokenTest_withWrongToken(){
        String tokenId = FAKE_TOKEN;
        Assertions.assertFalse(loginService.isValidToken(tokenId));
    }
}
