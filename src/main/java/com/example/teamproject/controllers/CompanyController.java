package com.example.teamproject.controllers;

import ch.qos.logback.core.model.Model;
import com.example.teamproject.entity.Company;
import com.example.teamproject.entity.SiteView;
import com.example.teamproject.entity.Users;
import com.example.teamproject.enums.MovingSize;
import com.example.teamproject.enums.Roles;
import com.example.teamproject.enums.States;
import com.example.teamproject.exceptions.FileException;
import com.example.teamproject.exceptions.PhoneNumberException;
import com.example.teamproject.repositories.ImageRepository;
import com.example.teamproject.services.CompanyService;
import com.example.teamproject.services.SiteViewService;
import com.example.teamproject.services.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    @RequestMapping(value = "/add_company", method = RequestMethod.GET)
    public ModelAndView addCompany(@RequestParam(value = "error", required = false) Boolean error,@RequestParam(value = "number-error", required = false) Boolean numberError,@RequestParam(value = "file-error", required = false) Boolean fileError) {
        ModelAndView modelAndView = new ModelAndView("addCompany");
        ArrayList<States> states = new ArrayList<States>(Arrays.asList(States.values()));
        modelAndView.addObject("company", new Company());
        modelAndView.addObject("states", states);
        if (error != null) {
            modelAndView.addObject("error", "Name is already taken");
        }
        if (numberError != null) {
            modelAndView.addObject("numberError", numberError);
        }
        if (fileError != null) {
            modelAndView.addObject("fileError", fileError);
        }
        return modelAndView;
    }

    @PostMapping(value = "/save_company")
    public String saveCompany(@ModelAttribute(name = "company") Company company, @RequestParam("file1") MultipartFile file1) throws IOException {
        try {
            companyService.saveCompany(company, file1);
            if (authenticatedUser().getRole().equals(Roles.USER)) {
                Users user = authenticatedUser();
                user.setRole(Roles.BUSINESSMEN);
                usersService.update(user);
                return "redirect:/";
            }else {
                return "redirect:/";
            }
        }catch (PhoneNumberException e){
            return "redirect:/add_company?number-error=true";
        }
        catch (FileException e){
            return "redirect:/add_company?file-error=true";
        }
        catch (Exception e){
            return "redirect:/add_company?error=true";
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
        ArrayList<States> states = new ArrayList<>(Arrays.asList(States.values()));
        ArrayList<MovingSize> movingSizes = new ArrayList<>(Arrays.asList(MovingSize.values()));
        modelAndView.addObject("allActiveCompanies", allActiveCompanies);
        modelAndView.addObject("states", states);
        modelAndView.addObject("movingSizes", movingSizes);
        modelAndView.addObject("siteView", siteViewService.getById(1));
        return modelAndView;
    }
    @PostMapping(value = "/delete_company")
    public String changeRating(@RequestParam(name = "companyId") Long companyId) {
        companyService.deleteCompany(companyId);
        return "redirect:/";
    }

    @GetMapping(value = "/updateCompany")
    public ModelAndView updateCompany(@RequestParam(value = "error", required = false) Boolean error,@RequestParam(value = "number-error", required = false) Boolean numberError,@RequestParam(value = "file-error", required = false) Boolean fileError,
                                      @RequestParam(name = "companyId") Long companyId) {
        ArrayList<States> states = new ArrayList<States>(Arrays.asList(States.values()));
        ModelAndView modelAndView = new ModelAndView("updateCompany");
        modelAndView.addObject("states", states);
        modelAndView.addObject("company", companyService.getCompanyById(companyId));
        if (error != null) {
            modelAndView.addObject("error", "Name is already taken");
        }
        if (numberError != null) {
            modelAndView.addObject("numberError", numberError);
        }
        if (fileError != null) {
            modelAndView.addObject("fileError", fileError);
        }
        return modelAndView;
    }


    @PostMapping(value = "/saveUpdateCompany")
    public String saveUpdate(@ModelAttribute(name = "company") Company company,@RequestParam("file1") MultipartFile file1, @RequestParam("companyId") Long Id) throws IOException{
        try {
            this.companyService.updateCompany(Id,company,file1);
            return "redirect:/";
        } catch (PhoneNumberException e){
            return "redirect:/add_company?number-error=true";
        }
        catch (FileException e){
            return "redirect:/add_company?file-error=true";
        }
        catch (Exception e){
            return "redirect:/add_company?error=true";
        }
    }
}
