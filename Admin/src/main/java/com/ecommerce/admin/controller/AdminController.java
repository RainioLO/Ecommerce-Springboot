package com.ecommerce.admin.controller;

import com.ecommerce.library.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController { // for the mapping

    @GetMapping("/categories")
    public String categories(Model model){
        model.addAttribute("title", "Category");
        return "categories"; // the html
    }
}
