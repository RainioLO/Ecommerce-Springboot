package com.ecommerce.library.service;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    Product save(ProductDto productDto);
    Product update(ProductDto productDto);
    void deleteById(Long id);
    void enableById(Long id);
}
