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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Почта или пароль неверны");
            model.setViewName("/login");
        }
        if (logout != null) {
            model.addObject("logout", "Logged out successfully.");
            model.setViewName("/login");
        }
        return model;
    }

    @RequestMapping(value = "/userMain", method = RequestMethod.GET)
    public String hello() {
        return "userMain";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new Users());
        return modelAndView;
    }


    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute(name = "user") Users user) {
        try {
            ModelAndView model = new ModelAndView();
            this.userService.save(user);
            return "login";
        } catch (Exception e) {
            return "registration";
        }
    }

    @RequestMapping(value = "/adminMain", method = RequestMethod.GET)
    public String adminMain() {
        return "adminMain";
    }
}
