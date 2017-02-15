package com.littleworld.login.services;

import java.util.List;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import com.littleworld.login.model.Login;

@Service
public class LoginService {

    List<Login> logins = new ArrayList<>();

    public Login createLogin(Login login ) {
        logins.add(login);
        return login;
    }

    public List<Login> getAllLogins() {
        return logins;
    }
 
    public Login getLoginById(int id) {
        return logins.get(id);
    }
}

