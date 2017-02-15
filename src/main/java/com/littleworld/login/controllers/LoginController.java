
package com.littleworld.login.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.littleworld.login.model.Login;
import com.littleworld.login.services.LoginService;

@Controller
public class LoginController {

    @Autowired  private LoginService loginService;

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("login", new Login());
        return "loginForm";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginSubmit(Login login) {
        loginService.createLogin(login);
        return "redirect:logins";
    }

   @RequestMapping(value="/login/{index}", method=RequestMethod.GET)
    public String loginFindById(@PathVariable int index, Model model) {
        Login login = loginService.getLoginById(index) ;
        model.addAttribute("login", login);
        return "loginView";
    }

    @RequestMapping(value="/logins", method=RequestMethod.GET)
    public String loginGetAll(Model model) {
        List<Login> logins = loginService.getAllLogins();
        model.addAttribute("logins", logins);
        return "loginList";
    }

    //curl  http://localhost:8080/login/list
    @ResponseBody
    @RequestMapping(value = "/listLogin", method = RequestMethod.GET)
    public List<Login> findAllLogins() {
      return loginService.getAllLogins();
    }
}

