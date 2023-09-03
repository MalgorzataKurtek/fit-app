package com.example.fitApp.controller;

import com.example.fitApp.dto.UserDTO;
import com.example.fitApp.entity.User;
import com.example.fitApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AllController {

    private UserService userService;

    public AllController (UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/index")
    public String home() {
        return "index";
    }



    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "register";
    }


    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDTO,
                               BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDTO.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "An account with this email address already exists.");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "/register";
        }

        userService.saveUser(userDTO);
        return "redirect:/register?success";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/userProfile")
    public String userProfile(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("email", principal.getName());
        }
        return "userProfile";
    }


}