package com.platform.product.controllers;

import com.platform.product.models.Product;
import com.platform.product.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest(ProductController.class)
class ProductControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    
    @Autowired
    private ProductController productController;
    
    @MockitoBean
    private ProductService productService;
    
    @Test
    void shouldReturnProductById() throws Exception {
        
        Long productId = 10L;
        Product product = new Product();
        product.setId(10L);
        
        when(productService.getProductById(productId))
                .thenReturn(product);
        
        mockMvc.perform(get("/products/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10));
    }
    
    @Test
    void getProductById() {
        Long productId = 10L;
        Product expectedProduct = new Product();
        expectedProduct.setId(10L);
        
        when(productService.getProductById(productId)).thenReturn(expectedProduct);
        ResponseEntity<Product> actualProductRes =productController.getProductById(10L);
        ResponseEntity<Product> expectedProductRes = ResponseEntity.status(HttpStatusCode.valueOf(200)).body(expectedProduct);
         assertEquals(expectedProductRes,actualProductRes);
    }
    
    @Test
    void getAllProducts() {
    }
}