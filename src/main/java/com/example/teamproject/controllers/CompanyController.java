package com.example.teamproject.controllers;

import com.example.teamproject.entity.Company;
import com.example.teamproject.entity.Image;
import com.example.teamproject.entity.Users;
import com.example.teamproject.enums.Roles;
import com.example.teamproject.enums.States;
import com.example.teamproject.repositories.ImageRepository;
import com.example.teamproject.services.CompanyService;
import com.example.teamproject.services.SiteViewService;
import com.example.teamproject.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private SiteViewService siteViewService;

    @PostMapping("/company/create")
    public String createCompany(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, Company company) throws IOException {
        companyService.saveCompany(company, file1, file2, file3);
        return "redirect:/";
    }
    @RequestMapping(value = "/add_company", method = RequestMethod.GET)
    public ModelAndView addCompany() {
        ModelAndView modelAndView = new ModelAndView("addCompany");
            ArrayList<States> states = new ArrayList<States>(Arrays.asList(States.values()));
            modelAndView.addObject("company", new Company());
            modelAndView.addObject("states", states);
        return modelAndView;
    }

    @PostMapping(value = "/save_company")
    public String saveCompany(@ModelAttribute(name = "company") Company company, @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                              @RequestParam("file3") MultipartFile file3) throws IOException {
        companyService.saveCompany(company, file1, file2, file3);
        if (authenticatedUser().getRole().equals(Roles.USER)) {
            Users user = authenticatedUser();
            user.setRole(Roles.BUSINESSMEN);
            usersService.update(user);
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }
    public Users authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = usersService.findByUserName(authentication.getName());
        return user;
    }

    @RequestMapping(value = "/get_all_companies", method = RequestMethod.GET)
    public ModelAndView getAllCompanies() {
        ModelAndView modelAndView = new ModelAndView("home");
        ArrayList<Company> allActiveCompanies = (ArrayList<Company>) companyService.getAllActiveCompanies();
        modelAndView.addObject("allActiveCompanies", allActiveCompanies);
        modelAndView.addObject("siteView", siteViewService.getById(1));
        return modelAndView;
    }
}
