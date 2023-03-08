package com.example.asserplus23.controller;

import com.example.asserplus23.daoService.ClientsDao;
import com.example.asserplus23.service.GeneratorService;
import com.example.asserplus23.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class RstController {
    @Autowired
    LoginService loginService;

    @Autowired
    GeneratorService generatorService;
    @Autowired
    ClientsDao clientsDao;

    @PostMapping("/checkConnexion")
    public String postCheckConnexion(@RequestParam("id")String identifiant, @RequestParam("pass") String password){
        String token = loginService.getToken(identifiant,password);
        if (token != null) {
            return token;
        }else {
            return "null";
        }
    }

    @PostMapping("/getClientSinistres")
    public ArrayList<Map> postGetClientSinistress(
            @RequestParam("personId") String personId,
            @RequestParam("token") String tokenIdentifiant)
    {
        if (!loginService.isValidToken(tokenIdentifiant)){
            System.out.println("null");
            return null;
        }
        Long clientId = clientsDao.getClientByPersonId(Long.valueOf(personId)).getId();
        return generatorService.generateSinistresDetails(clientId);

    }
}
