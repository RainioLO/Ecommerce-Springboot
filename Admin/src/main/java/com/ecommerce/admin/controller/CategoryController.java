package com.ecommerce.admin.controller;

import com.ecommerce.library.model.Category;
import com.ecommerce.library.repository.CategoryRepository;
import com.ecommerce.library.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String categories(Model model, RedirectAttributes redirectAttributes) {
        Map<String, ?> flashAttributes = redirectAttributes.getFlashAttributes();
        String error = (String) flashAttributes.get("error");
        if (error != null) {
            model.addAttribute("error", error);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Manage Category");
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("size", categories.size());
        model.addAttribute("categoryNew", new Category());
        return "categories";
    }

    @PostMapping("/save-category")
    public String save(@ModelAttribute("categoryNew") Category category, RedirectAttributes redirectAttributes) {
        if (category == null) {
            redirectAttributes.addFlashAttribute("error", "The category cannot be null.");
            return "redirect:/categories";
        }
        try {
            categoryService.save(category);
            redirectAttributes.addFlashAttribute("success", "Category added successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Duplicate name of category, please check again!");
            logError(e, "DataIntegrityViolationException: Duplicate category name.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error occurred on the server.");
            logError(e, "Exception: Error occurred on the server.");
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.PUT})
    @ResponseBody
    public Optional<Category> findById(Long id){
        return categoryService.findById(id);
    }

    @GetMapping("/update-category")
    public String update(Category category, RedirectAttributes redirectAttributes){
        try{ // categories.html link with <form th:action="@{/update-category}" method="put">
            category.set_activated(true);
            categoryService.update(category); //update with responseBody from /findById
            redirectAttributes.addFlashAttribute("success", "Updated successfully"); // redirect with "success"
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Fail in Updating Category because duplicate name"); // redirect with "failed"
        } catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("failed", "Error server"); // redirect with "failed"
        }
        return "redirect:/categories"; // direct to /categories endpoint after update
        }
        
    @RequestMapping(value = "/delete-category", method = {RequestMethod.GET, RequestMethod.PUT})
    public String delete(Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Category deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error occurred on the server.");
            logError(e, "Exception: Error occurred on the server.");
        }
        return "redirect:/categories";
        }

    @RequestMapping(value = "/enable-category", method = {RequestMethod.GET, RequestMethod.PUT})
    public String enable(Long id, RedirectAttributes redirectAttributes) {
        try {
            categoryService.enabledById(id);
            redirectAttributes.addFlashAttribute("success", "Category enabled successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error occurred on the server.");
            logError(e, "Exception: Error occurred on the server.");
        }
        return "redirect:/categories";
        }



    private void logError(Exception e, String message) {
        Logger logger = LoggerFactory.getLogger(CategoryController.class);
        logger.error(message, e);
    }
}
