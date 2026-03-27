package com.platform.product.client;

import com.platform.product.dtos.FakeStoreProductDto;
import com.platform.product.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="product-service",
url="${feign.client.product-service.url}")
public interface ProductClient {
    
   
    @GetMapping("/{id}")
    public ResponseEntity<FakeStoreProductDto> getProductById(@PathVariable("id") Long id);
}
