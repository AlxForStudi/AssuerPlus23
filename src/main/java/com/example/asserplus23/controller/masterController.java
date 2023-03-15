package com.example.asserplus23.controller;

import com.example.asserplus23.daoService.*;
import com.example.asserplus23.model.*;
import com.example.asserplus23.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.function.LongToIntFunction;

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
    FilesDao filesDao;
    @Autowired
    LogginsDao logginsDao;
    @Autowired
    UploadFilesService uploadFilesService;
    @Autowired
    CheckFormService checkFormService;
    @Autowired
    GeneratorService generatorService;
    @Autowired
    LoginService loginService;

    @GetMapping("/home")
    public String getHome(Model model){
        model.addAttribute("errorMsg","");
        return "home";
    }

    @GetMapping("/menu")
    public String getMenu(Model model,@RequestParam("userName") String userName, @RequestParam("token") String tokenIdentifiant){
        if (!loginService.isValidToken(tokenIdentifiant)){
            model.addAttribute("errorMsg","Token obselete, il va falloir vous reconnecter!");
            return "home";
        }
        Long clientID = clientsDao.getClientByLogId(logginsDao.getLogginByIdentifiant(userName).getId()).getId();
        Clients CLIENTS_ON = clientsDao.getClient(clientID);
        Persons PERSONS_ON = personsDao.getPerson(CLIENTS_ON.getPersonid());

        model.addAttribute("user", PERSONS_ON);
        model.addAttribute("token", tokenIdentifiant);
        model.addAttribute("userName", userName);
        return "menu";
    }

    @GetMapping("/myDeclarations")
    public String getmyDeclarations(Model model,@RequestParam("userName") String userName, @RequestParam("token") String tokenIdentifiant){
        if (!loginService.isValidToken(tokenIdentifiant)){
            model.addAttribute("errorMsg","Token obselete, il va falloir vous reconnecter!");
            return "home";
        }
        Long clientID = clientsDao.getClientByLogId(logginsDao.getLogginByIdentifiant(userName).getId()).getId();
        Clients CLIENTS_ON = clientsDao.getClient(clientID);
        Persons PERSONS_ON = personsDao.getPerson(CLIENTS_ON.getPersonid());

        model.addAttribute("user", PERSONS_ON);
        model.addAttribute("token", tokenIdentifiant);
        model.addAttribute("userName", userName);
        return "myDeclarations";
    }

    @GetMapping ("/formNewDeclaration")
    public String getFormNewDeclaration(Model model,@RequestParam("userName") String userName, @RequestParam("token") String tokenIdentifiant){
        if (!loginService.isValidToken(tokenIdentifiant)){
            model.addAttribute("errorMsg","Token obselete, il va falloir vous reconnecter!");
            return "home";
        }
        Long clientID = clientsDao.getClientByLogId(logginsDao.getLogginByIdentifiant(userName).getId()).getId();
        Clients CLIENTS_ON = clientsDao.getClient(clientID);
        Persons PERSONS_ON = personsDao.getPerson(CLIENTS_ON.getPersonid());
        /*Passage info à la page */
        model.addAttribute("clientNumber", CLIENTS_ON.getUsernumber());
        model.addAttribute("user", PERSONS_ON);
        model.addAttribute("listContract", contractsDao.getContractsByClientsId(CLIENTS_ON.getId()));
        model.addAttribute("token", tokenIdentifiant);
        model.addAttribute("userName", userName);
        return "newDeclaration";
    }

    @PostMapping("/newDeclaration")
    public String postDeclaration(
            Model model,
            @RequestParam("photo") ArrayList<MultipartFile> photos,
            @RequestParam("constat") ArrayList<MultipartFile> constats,
            @RequestParam("contracts") String contract,
            @RequestParam("where") String lieu,
            @RequestParam("clientNumber") String clientNumber,
            @RequestParam("when") String date,
            @RequestParam("userName") String userName,
            @RequestParam("token") String tokenIdentifiant
    )
    {
        if (!loginService.isValidToken(tokenIdentifiant)){
            model.addAttribute("errorMsg","Token obselete, il va falloir vous reconnecter!");
            return "home";
        }
        Clients CLIENTS_ON = clientsDao.getClientByUserNumber(clientNumber);
        Persons PERSONS_ON = personsDao.getPerson(CLIENTS_ON.getPersonid());
        String errorMsg;
        errorMsg = checkFormService.checkData(photos,constats,date);
        Contracts selectContract = contractsDao.getContractByCode(contract);
        if (errorMsg.equals("")){
            /*Generation nouveau Sinistre*/
            Sinistres newSinistres = generatorService.generateSinistre(selectContract,date,lieu);
            /*Upload des fichier dans répertoire temporaire*/
            errorMsg = uploadFilesService.uploadFilesTemp(photos,newSinistres,CLIENTS_ON.getId(),"Photos");
            errorMsg.concat(uploadFilesService.uploadFilesTemp(constats,newSinistres,CLIENTS_ON.getId(),"Constat"));

            if (errorMsg.equals("")){
                /*Création Sinistres en BDD*/
                sinistresDao.addSinistre(newSinistres);
                /*Récupération de l'ID du sinistre créé*/
                newSinistres.setId(sinistresDao.getSinistreByCode(newSinistres.getCode()).getId());
                /*Création Files en BDD*/
                for (Files file : generatorService.generateFilesList(uploadFilesService.getTempFilesByClientsId(CLIENTS_ON.getId()))){
                    file.setSinistreid(newSinistres.getId());
                    filesDao.addFile(file);
                }
                /*Upload Temp files dans /uploads*/
                uploadFilesService.copyFilesToUploads(uploadFilesService.getTempFilesByClientsId(CLIENTS_ON.getId()),CLIENTS_ON.getId());
                /*Passage info à la page */
                model.addAttribute("user", PERSONS_ON);
                model.addAttribute("msg","A bien été prise en compte !");
                model.addAttribute("contract", selectContract);
                model.addAttribute("sinistres", newSinistres);
                model.addAttribute("token", tokenIdentifiant);
                model.addAttribute("userName", userName);


                return "declarationDone";

            }
        }
        /*Passage info à la page */
        model.addAttribute("user",PERSONS_ON);
        model.addAttribute("listContract", contractsDao.getContractsByClientsId(CLIENTS_ON.getId()));
        model.addAttribute("msg", errorMsg);
        model.addAttribute("error",true);
        model.addAttribute("preSetContract",selectContract);
        model.addAttribute("where",lieu);
        model.addAttribute("when",date);
        model.addAttribute("token", tokenIdentifiant);
        model.addAttribute("userName", userName);

        return "newDeclaration";
    }
}
