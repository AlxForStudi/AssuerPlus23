package com.example.asserplus23.service;

import com.example.asserplus23.daoService.ClientsDao;
import com.example.asserplus23.daoService.LogginsDao;
import com.example.asserplus23.daoService.TokensDao;
import com.example.asserplus23.model.Loggins;
import com.example.asserplus23.model.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class LoginService {
    @Autowired
    LogginsDao logginsDao;
    @Autowired
    HashService hashService;
    @Autowired
    ClientsDao clientsDao;
    @Autowired
    TokensDao tokensDao;

    public String getToken(String identifiant, String password){
        Loggins clientLoggins = logginsDao.getLogginByIdentifiant(identifiant);
        if (clientLoggins==null){
            return null;
        }
        if (!hashService.isHashEqual(clientLoggins.getPassWord(),password,clientLoggins.getSalt())){
            return null;
        }
        String tokenIdentifiant = hashService.getSalt(64);
        Tokens t = new Tokens(hashService.getHash(tokenIdentifiant));
        tokensDao.addToken(t);
        return tokenIdentifiant;
    }

    public boolean isValidToken(String tokenIdentifiant){
        String hashId = hashService.getHash(tokenIdentifiant);
        Tokens t = tokensDao.getTokenByIdentifiant(hashId);
        if (t == null){
            return false;
        }
        return LocalDateTime.now(ZoneId.of("Europe/Paris")).isBefore(LocalDateTime.parse(t.getValidity()));
    }
}
