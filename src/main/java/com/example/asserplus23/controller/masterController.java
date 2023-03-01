package com.example.asserplus23.controller;

import com.example.asserplus23.daoService.ClientsDao;
import com.example.asserplus23.daoService.ContractsDao;
import com.example.asserplus23.daoService.PersonsDao;
import com.example.asserplus23.daoService.SinistresDao;
import com.example.asserplus23.model.*;
import com.example.asserplus23.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Controller
public class masterController {
    @Autowired
    PersonsDao personsDao;
    @Autowired
    ContractsDao contractsDao;
    @Autowired
    SinistresDao sinistresDao;
    @Autowired
    ClientsDao clientsDao;
    @Autowired
    UploadFilesService uploadFilesService;
    @Autowired
    CheckFormService checkFormService;
    @Autowired
    GeneratorService generatorService;



    @GetMapping("/")
    public String getNewDeclaration(Model model){
     /**Fake récup info user **/
     Clients client = clientsDao.getClient(1L);
     Persons user = personsDao.getPerson(client.getPersonid());

        /*Passage info à la page */
        model.addAttribute("user", user);
        model.addAttribute("listContract", contractsDao.getContractsByClientsId(client.getId()));

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
        String errorMsg;
        errorMsg = checkFormService.checkData(photos,constats,date);
        Contracts selectContract = contractsDao.getContractByCode(contract);
        if (errorMsg.equals("")){

            /**Fake récup info user **/
            Clients client = clientsDao.getClient(1L);

            Sinistres newSinistres = generatorService.generateSinistre(selectContract,date,lieu);
            errorMsg = uploadFilesService.uploadFilesTemp(photos,newSinistres,client.getId(),"Photos");
            errorMsg.concat(uploadFilesService.uploadFilesTemp(constats,newSinistres,client.getId(),"Constat"));

            if (errorMsg.equals("")){
                for (Files file : generatorService.generateFilesList(uploadFilesService.getTempFilesByClientsId(client.getId()))){
                    System.out.println(file);
                }
                /*sinistresService.addSinistre(newSinistres);*/

                /**Fake récup info user **/
                /*Clients client = clientsService.getClient(1L);*/
                Persons user = personsDao.getPerson(client.getPersonid());

                /*Passage info à la page */
                model.addAttribute("user", user);
                model.addAttribute("msg","A bien été prise en compte !");
                model.addAttribute("contract", selectContract);
                model.addAttribute("sinistres", newSinistres);

                return "declarationDone";

            }
        }
        /**Fake récup info user **/
        Clients client = clientsDao.getClient(1L);
        Persons user = personsDao.getPerson(client.getPersonid());

        /*Passage info à la page */
        model.addAttribute("user",user);
        model.addAttribute("listContract", contractsDao.getContractsByClientsId(client.getId()));
        model.addAttribute("msg", errorMsg);
        model.addAttribute("error",true);
        model.addAttribute("preSetContract",selectContract);
        model.addAttribute("where",lieu);
        model.addAttribute("when",date);

        return "newDeclaration";
    }
}
