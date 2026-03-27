package com.platform.gateway;

import com.platform.gateway.client.ProductClient;
import com.platform.gateway.dtos.FakeStoreProductDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    private final ProductClient productClient;
    
    public ApiController(ProductClient productClient) {
        this.productClient = productClient;
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<FakeStoreProductDto> getProductById(@PathVariable("id") Long id) {
        System.out.println(productClient.getProductById(id).getBody());
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(productClient.getProductById(id).getBody());
       
    }
}
