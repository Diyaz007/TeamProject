package com.example.teamproject.controllers;

import com.example.teamproject.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.teamproject.entity.Users;

@Controller
public class UsersController {
    @Autowired
    private UsersService userService;

    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/mainUser")
    public String mainUser() {
        return "mainUser";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping(value = "/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new Users());
        return modelAndView;
    }

    @GetMapping(value = "addCompany")
    public String addCompany() {return "addCompany";}

    @GetMapping(value = "getQuote")
    public String getQuote() {return "getQuote";}


    @PostMapping(value = "/register")
    public String registration(@ModelAttribute(name = "user") Users user) {
        try {
            this.userService.save(user);
            return "login";
        } catch (Exception e) {
            return "registration";
        }
    }
}
