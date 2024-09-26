package com.example.teamproject.controllers;

import com.example.teamproject.entity.Company;
import com.example.teamproject.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/company/create")
    public String createCompany(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, Company company) throws IOException {
        companyService.saveCompany(company, file1, file2, file3);
        return "redirect:/";
    }
}
