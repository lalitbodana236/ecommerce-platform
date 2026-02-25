package com.platform.product.services.impl;

import com.platform.product.models.Product;
import com.platform.product.respository.ProductRepository;
import com.platform.product.services.ProductService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class SelfProductService implements ProductService {
    
    private ProductRepository productRepository;
    
    public SelfProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Override
    public Product getProductById(Long productId) {
        return this.productRepository.findProdcutById(productId);
    }
    
    @Override
    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }
}
