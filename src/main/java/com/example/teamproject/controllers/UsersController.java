package com.example.teamproject.controllers;

import com.example.teamproject.entity.Company;
import com.example.teamproject.exceptions.EmailException;
import com.example.teamproject.exceptions.PasswordException;
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

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "notFound", required = false) Boolean notFoundError) {
        ModelAndView modelAndView = new ModelAndView("login");
        if (notFoundError != null) {
            modelAndView.addObject("notFoundError", notFoundError);
        }
        return modelAndView;
    }


    @GetMapping(value = "/register")
    public ModelAndView register(@RequestParam(value = "login-error", required = false) Boolean loginError,@RequestParam(value = "email-error", required = false) Boolean emailError,@RequestParam(value = "password-error", required = false) Boolean passwordError) {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new Users());
        if (loginError != null) {
            modelAndView.addObject("loginError", loginError);
        }
        if (emailError != null) {
            modelAndView.addObject("emailError", emailError);
        }
        if (passwordError != null) {
            modelAndView.addObject("passwordError", passwordError);
        }
        return modelAndView;
    }


    @PostMapping(value = "/register")
    public String registration(@ModelAttribute(name = "user") Users user) {
        try {
            this.userService.save(user);
            return "login";
        } catch (EmailException e) {
            return "redirect:/register?email-error=true";
        } catch (PasswordException e) {
            return "redirect:/register?password-error=true";
        } catch ( Exception e){
            return "redirect:/register?login-error=true";
        }
    }
}