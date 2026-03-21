package com.career.CareerSync.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @GetMapping("/")
    public String getCompanyManagementDash(){

        return "";
    }
}
