package com.example.asserplus23.servicesTest;

import com.example.asserplus23.daoService.*;
import com.example.asserplus23.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class EntitiesServiceTest {

    @Autowired
    private PersonsDao personsDao = new PersonsDao();
    @Autowired
    private ClientsDao clientsDao = new ClientsDao();
    @Autowired
    private CompaniesDao companiesDao = new CompaniesDao();
    @Autowired
    private ContractsDao contractsDao = new ContractsDao();
    @Autowired
    private FilesDao filesDao = new FilesDao();
    @Autowired
    private LogginsDao logginsDao = new LogginsDao();
    @Autowired
    private SinistresDao sinistresDao = new SinistresDao();
    @Autowired
    private TokensDao tokensDao = new TokensDao();
    private static Persons JOHN_Persons;
    private static Clients JOHN_Clients;
    private static Contracts JOHN_Contracts;
    private static Companies JOHN_Companies;
    private static Files JOHN_Files;
    private static Loggins JOHN_Loggins;
    private static Sinistres JOHN_Sinistres;

    @BeforeAll
    public static void beforeAll(){
        JOHN_Persons = new Persons(
                1L,
                "Dupont",
                "John",
                "7 rue des merveilles, Entrepreneur city",
                "johndupont@email.fr",
                "0650505050",
                1L);
        JOHN_Clients = new Clients(1L,"UC230223JD",1L,1L);
        JOHN_Contracts = new Contracts(1L,1L,"Megane-John","VP1234JDRM");
        JOHN_Companies = new Companies(
                1L,
                "john & Co",
                "12345678912345",
                "8 boulevard des entreprises, Saint Business",
                "john-Co@email.com",
                "0250505050",
                "0251515151");
        JOHN_Files = new Files(
                1L,
                "AVP230223AConstat.pdf",
                "application/PDF",
                "/uploads",
                1L);
        JOHN_Loggins = new Loggins(1L,"johndupont44","JohnDupont1234%","A");
        JOHN_Sinistres= new Sinistres(
                1L,
                1L,
                "AVP230223A",
                "10 boulevard des entreprises, Saint Business",
                "23-02-2023",
                "Non traiter");
    }

    @Test
    public void getPersonsTest(){
        Assertions.assertTrue(personsDao.getPerson(1L).equals(JOHN_Persons));
    }
    @Test
    public void getClientTest(){
        Assertions.assertTrue(clientsDao.getClient(1L).equals(JOHN_Clients));
    }
    @Test
    public void getContractTest(){
        Assertions.assertTrue(contractsDao.getContract(1L).equals(JOHN_Contracts));
    }
    @Test
    public void getFileTest(){
        Assertions.assertTrue(filesDao.getFile(1L).equals(JOHN_Files));
    }
    @Test
    public void getLogginTest(){Assertions.assertTrue(logginsDao.getLoggin(1L).equals(JOHN_Loggins));}
    @Test
    public void getSinistreTest(){Assertions.assertTrue(sinistresDao.getSinistre(1L).equals(JOHN_Sinistres));}
    @Test
    public void getCompanyTest(){Assertions.assertTrue(companiesDao.getCompanies(1L).equals(JOHN_Companies));}

}
