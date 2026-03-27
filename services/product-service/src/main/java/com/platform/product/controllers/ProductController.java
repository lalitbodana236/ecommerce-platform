package com.platform.product.controllers;

import com.platform.product.client.ProductClient;
import com.platform.product.commons.AuthCommons;
import com.platform.product.dtos.FakeStoreProductDto;
import com.platform.product.models.Category;
import com.platform.product.models.Product;
import com.platform.product.services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;
    
    private final ProductClient productClient;
    
    private final AuthCommons authCommons;
    
    public ProductController(ProductService productService, ProductClient productClient, AuthCommons authCommons) {
        this.productService = productService;
        this.productClient = productClient;
        this.authCommons = authCommons;
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<FakeStoreProductDto> getProductById(@PathVariable("id") Long id) {
    //  boolean isValid =   this.authCommons.validateToken(token);
       // System.out.println(isValid);
        System.out.println("testing done");
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(productClient.getProductById(id).getBody());
      //  return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(this.productService.getProductById(id));
    }
    
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImageUrl());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        
        return product;
    }
    
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(200).body(this.productService.getAllProduct());
    }
    
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(product);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
        return ResponseEntity.ok(product);
    }
    
    @PutMapping
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
        return ResponseEntity.ok(product);
    }
    
    @DeleteMapping
    public ResponseEntity deleteMapping(@PathVariable("id") Long id) {
        return ResponseEntity.status(204).build();
    }
    
}
