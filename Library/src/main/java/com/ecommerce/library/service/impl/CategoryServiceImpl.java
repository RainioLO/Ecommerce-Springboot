package com.ecommerce.library.service.impl;

import com.ecommerce.library.model.Category;
import com.ecommerce.library.repository.CategoryRepository;
import com.ecommerce.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) { // someone pass by model attribute , an object
        try{
            Category categorySave = new Category(category.getName()); // new an object and save
            // categorySave.set_deleted(true);
            return categoryRepository.save(categorySave);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // @Override
    // public Optional<Category> findById(Long id) {
    //     return categoryRepository.findById(id);
    // }

    @Override
    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    @Override
    public Category update(Category category) { // in a reference Object, and add modified object into the database
        Category categoryUpdate = null; // input the responseBody from the findById ??
        try {
            categoryUpdate = categoryRepository.findById(category.getId()).get(); //onlyread to the category
            categoryUpdate.setName(category.getName()); //still that object , the responseBody, when changes through js -> return the name to the new object and save
            categoryUpdate.set_activated(category.is_activated());
            categoryUpdate.set_deleted(category.is_deleted());
            return categoryRepository.save(categoryUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryRepository.save(categoryUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository.getReferenceById(id); // get the category object from database, change status and save to the database
        category.set_activated(false);
        category.set_deleted(true);
        categoryRepository.save(category);
    }

    @Override
    public void enabledById(Long id) {
        Category category = categoryRepository.getReferenceById(id);
        category.set_activated(true);
        category.set_deleted(false);
        categoryRepository.save(category);
    }
}
