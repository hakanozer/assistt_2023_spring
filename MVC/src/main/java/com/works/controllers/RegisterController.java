package com.works.controllers;

import com.works.entities.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("login", new Login());
        return "register";
    }

    @PostMapping("/adminRegister")
    public String adminRegister(@Valid Login login, BindingResult binding, Model model) {
        if ( binding.hasErrors() ) {
            model.addAttribute("errors", binding.getFieldErrors());
            return "register";
        }
        System.out.println(login);
        return "redirect:/";
    }

}
