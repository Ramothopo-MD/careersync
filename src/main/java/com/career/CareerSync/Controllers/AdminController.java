package com.career.CareerSync.Controllers;

import com.career.CareerSync.DTO.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/careersync")
public class AdminController {
  @GetMapping("/admin/dashboard")
  public String getAdminDashboard( Model model){

      model.addAttribute("loginDTO",new LoginDTO());

      return "UserDashboard";
  }

}
