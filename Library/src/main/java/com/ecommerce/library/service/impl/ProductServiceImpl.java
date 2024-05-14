package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.ProductDto;
import com.ecommerce.library.mapper.ProductMapper;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.repository.ProductRepository;
import com.ecommerce.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> findAll() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product product : products){
            ProductDto productDto = ProductMapper.productMapper(product);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public Product save(ProductDto productDto) {
        return null;
    }

    @Override
    public Product update(ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void enableById(Long id) {

    }
}
