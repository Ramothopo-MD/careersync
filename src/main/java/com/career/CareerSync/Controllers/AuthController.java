package com.career.CareerSync.Controllers;

import com.career.CareerSync.DTO.LoginDTO;
import com.career.CareerSync.DTO.UserDTO;
import com.career.CareerSync.Models.MyUser;
import com.career.CareerSync.Models.Security;
import com.career.CareerSync.Repositories.SecurityRepository;
import com.career.CareerSync.Repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private SecurityRepository sr;

    @Autowired
    private UserRepository ur;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "RegisterAccount";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {

        model.addAttribute("loginDTO",new LoginDTO());

        return "Login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginDTO") LoginDTO loginDTO,
                        BindingResult bindingResult,
                        Model model){

        // Validate empty fields
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error));
            return "Login";
        }

        Optional<MyUser> userOpt=ur.findByEmail(loginDTO.getEmail());
        if (!userOpt.isPresent()){
            bindingResult.rejectValue("email",
                    "error.email",
                    "The following username does not exist");

            return "Login";
        }
        MyUser user = userOpt.get();
        String storedHash = user.getSecInfo().getPasswordHash();

        // Correct password check
        if (!passwordEncoder.matches(loginDTO.getPassword(), storedHash)) {
            bindingResult.rejectValue("password", "error.password", "Incorrect password");
            return "Login";
        }

        return "redirect:/careerSync/dashboard";
    }

    @PostMapping("/create-account")
    public String createAccount(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                                BindingResult bindingResult,
                                Model model) {

        // Log binding errors for debugging
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error));
            return "RegisterAccount";
        }

        // Confirm password check
        if(!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", "Passwords do not match");
            return "RegisterAccount";
        }

        // Check if email exists
        if (ur.findByEmail(userDTO.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "error.email", "The email already exists in our database.");
            return "RegisterAccount";
        }

        // Check if phone exists (optional)
        if(userDTO.getPhoneNumber() != null && !userDTO.getPhoneNumber().isBlank()) {
            if (ur.findByphoneNumber(userDTO.getPhoneNumber()).isPresent()) {
                bindingResult.rejectValue("phoneNumber", "error.phoneNumber", "The phone number already exists in our database.");
                return "RegisterAccount";
            }
        }

        // Save Security info
        Security security = new Security();
        security.setSecurityQuestion(userDTO.getSecurityQuestion());
        security.setSecurityAnswerHash(passwordEncoder.encode(userDTO.getSecurityAnswer()));
        security.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        security.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        sr.save(security);

        // Save User info
        MyUser user = new MyUser();
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setRole(userDTO.getRole());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setProfileUrl(userDTO.getProfileUrl());
        user.setSecInfo(security);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        ur.save(user);

        return "redirect:/auth/login";
    }
}
