package com.example.asserplus23.servicesTest;

import com.example.asserplus23.daoService.LogginsDao;
import com.example.asserplus23.model.Loggins;
import com.example.asserplus23.service.HashService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class HashServiceTest {

    @Autowired
    private HashService testHashService = new HashService();
    @Autowired
    private LogginsDao logginsDao = new LogginsDao();

    private static String MAG_MDP;
    private static String JHON_MDP;
    private static Long CLIENT_ONE_ID;
    private static Long CLIENT_TWO_ID;

    @BeforeAll
    public static void beforeAll(){
        MAG_MDP = "MagagaBest1234%%";
        JHON_MDP = "JohnDupont1234%";
        CLIENT_ONE_ID = 1l;
        CLIENT_TWO_ID = 2L;
    }
    @Test
    public void isHashEqualTest(){
        /*Récupération des Logins de l'utilisateur*/
        Loggins logOne = logginsDao.getLoggin(CLIENT_ONE_ID);

        /*Init variables neccessaires à la fonction*/
        String salt = logOne.getSalt();
        String hash = logOne.getPassWord();

        /*Execution fonction & Test*/
        Assertions.assertTrue(testHashService.isHashEqual(hash,JHON_MDP,salt));
        System.out.println("exec isHashEqualTest");
    }

}
