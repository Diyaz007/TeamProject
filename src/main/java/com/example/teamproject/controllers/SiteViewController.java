package com.example.teamproject.controllers;

import com.example.teamproject.entity.SiteView;
import com.example.teamproject.entity.Users;
import com.example.teamproject.services.SiteViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SiteViewController {
    @Autowired
    private SiteViewService siteViewService;

    @GetMapping(value = "/updateMainPage")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("updatePage");
        modelAndView.addObject("siteView", siteViewService.getById(1));
        return modelAndView;
    }


    @PostMapping(value = "/updateMainPage")
    public String registration(@ModelAttribute(name = "siteView") SiteView siteView) {
        try {
            this.siteViewService.update(siteView);
            return "redirect:/";
        } catch (Exception e) {
            return "registration";
        }
    }
}
