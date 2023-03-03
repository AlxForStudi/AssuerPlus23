package com.example.asserplus23.controller;

import com.example.asserplus23.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RstController {
    @Autowired
    LoginService loginService;
    @PostMapping("/checkConnexion")
    public String postCheckConnexion(@RequestParam("id")String identifiant, @RequestParam("pass") String password, Model model){
        String token = loginService.getToken(identifiant,password);
        if (token != null) {
            return token;
        }else {
            return "null";
        }
    }
}
