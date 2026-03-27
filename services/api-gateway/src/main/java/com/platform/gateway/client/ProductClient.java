package com.platform.gateway.client;

import com.platform.gateway.dtos.FakeStoreProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="product-service")
public interface ProductClient {
    
    
    @GetMapping("/products/{id}")
    public ResponseEntity<FakeStoreProductDto> getProductById(@PathVariable("id") Long id);
}
