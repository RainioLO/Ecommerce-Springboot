package com.ecommerce.admin.controller;

import com.ecommerce.library.dto.AdminDto;
import com.ecommerce.library.model.Admin;
import com.ecommerce.library.service.impl.AdminServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @RequestMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/register") // when someone call this, new a AdminDto
    public String register(Model model) {
        model.addAttribute("adminDto", new AdminDto()); // new a AdminDTO and treat it as the attribute of this page
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){ //add model, link model to AdminDto()
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto")AdminDto adminDto,
                              BindingResult result,
                              Model model){

        try {
            // session.removeAttribute("message");
            if (result.hasErrors()){
                model.addAttribute("adminDto", adminDto); // adding from outside
                result.toString();
                return "register"; //return the html
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username); // get the admin by DTO
            if (admin != null){
                model.addAttribute("adminDto", adminDto);
                // see frontend register Line 38
                System.out.println("admin not null");
                // session.setAttribute("message", "Your email has been registered");
                model.addAttribute("emailError", "Your email has been registered"); // register.html line 38
                return "register"; //return to the register.html
            }
            if (adminDto.getPassword().equals(adminDto.getRepeatPassword())){
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                System.out.println("success");
                model.addAttribute("success", "Register successfully!");
                // session.setAttribute("message", "Register successfully!");
                model.addAttribute("adminDto", adminDto); // see register.html line 42
            } else {
                model.addAttribute("adminDto", adminDto);
                model.addAttribute("passwordError", "Your password maybe wrong! Check again !");
                // session.setAttribute("message", "Password is not same !"); //add session here, make it cautious
                System.out.println("password not same");
                return "register"; //redirect to the register page
            }
        } catch (Exception e){
            e.printStackTrace();
            // session.setAttribute("message", "Sever error, please try again later!");
            model.addAttribute("errors", "The server has been wrong");
        }
        return "register";
    }
}
