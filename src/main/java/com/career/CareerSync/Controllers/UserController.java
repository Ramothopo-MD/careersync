package com.career.CareerSync.Controllers;

import com.career.CareerSync.DTO.LoginDTO;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/careerSync")
public class UserController {
@GetMapping("/user/dashboard")
    public String getUserDashboard( Model model){

    model.addAttribute("loginDTO",new LoginDTO());

    return "UserDashboard";
}

}
