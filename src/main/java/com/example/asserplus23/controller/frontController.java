package com.example.asserplus23.controller;

import com.example.asserplus23.exception.IncorrectFormException;
import com.example.asserplus23.model.Contract;
import com.example.asserplus23.model.User;
import com.example.asserplus23.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
public class frontController {

    @Autowired
    UploadService uploadService = new UploadService();
    @GetMapping("/")
    public String getDeclarationPage(Model model)
    {
        /** Fake recupération de la list des informations neccesaires (viendra de la BDD)**/
        ArrayList<Contract> listContract = new ArrayList<>();
        listContract.add(new Contract("Voiture 1","BF805GF5"));
        listContract.add(new Contract("Voiture 2","BF805GF6"));
        listContract.add(new Contract("Voiture 3","BF805GF8"));
        User user = new User("Dupont","Jean","7 rue des merveilles","Dupontjean@leclate.fr");

        /**Envoi informations à la page**/
        model.addAttribute("user",user);
        model.addAttribute("listContract",listContract);


        return "newDeclaration";
    }

   @PostMapping("/")
   public String postDeclaration(
           Model model,
           @RequestParam("photo") ArrayList<MultipartFile> photos,
           @RequestParam("constat") ArrayList<MultipartFile> constats,
           @RequestParam("contracts") String contract,
           @RequestParam("where") String lieu,
           @RequestParam("when") String date
   )
   {
       String msg;
       Boolean error;
       try{
           msg = uploadService.uploadForm(photos,constats,contract,lieu,date);
           error = false;
       }catch (IncorrectFormException e){
           msg = e.getMessage();
           error = true;
       }
       if (!error) {
           User user = new User("Dupont", "Jean", "7 rue des merveilles", "Dupontjean@leclate.fr");
           model.addAttribute("user", user);
           model.addAttribute("msg", msg);
           return "declarationDone";
       }else {

           /** Fake recupération de la list des informations neccesaires (viendra de la BDD)**/
           ArrayList<Contract> listContract = new ArrayList<>();
           listContract.add(new Contract("Voiture 1","BF805GF5"));
           listContract.add(new Contract("Voiture 2","BF805GF6"));
           listContract.add(new Contract("Voiture 3","BF805GF8"));
           User user = new User("Dupont","Jean","7 rue des merveilles","Dupontjean@leclate.fr");
           Contract preSetContract = new Contract();
           /** a transformer en méthode getContract**/
           for (Contract elem : listContract){
               if (elem.getNumber().equals(contract)){
                   preSetContract = elem;
               }
           }

           /**Envoi informations à la page pour rechargement avec pre-selection**/
           model.addAttribute("user",user);
           model.addAttribute("listContract",listContract);
           model.addAttribute("msg", msg);
           model.addAttribute("error",error);
           model.addAttribute("preSetContract",preSetContract);
           model.addAttribute("where",lieu);
           model.addAttribute("when",date);
           return "newDeclaration";
       }

   }

}


