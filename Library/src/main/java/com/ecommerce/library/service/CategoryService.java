package com.ecommerce.library.service;

import com.ecommerce.library.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Category save(Category category);
    // Category findById(Long id);
    Optional<Category> findById(Long id);
    Category update(Category category);
    void deleteById(Long id);
    void enabledById(Long id);
    List<Category> findAllByActivated ();
}
