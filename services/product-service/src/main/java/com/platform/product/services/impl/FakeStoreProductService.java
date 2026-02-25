package com.platform.product.services.impl;

import com.platform.product.dtos.FakeStoreProductDto;
import com.platform.product.exception.ResourceNotFoundException;
import com.platform.product.models.Category;
import com.platform.product.models.Product;
import com.platform.product.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    
    private final RestTemplate restTemplate;
    
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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
    //This Service Implement use Fake Store to fetch product from fake store
    
    @Override
    public Product getProductById(Long productId) {
        //Make a Api call to Fake Store and get the product with the given Id.
        FakeStoreProductDto productDto = this.restTemplate
                                                 .getForObject(
                                                         "https://fakestoreapi.com/products/" + productId,
                                                         FakeStoreProductDto.class
                                                 );
        // Convert FakeStore to Product Object
        if(productDto==null) throw new ResourceNotFoundException("Product not found");
        return convertFakeStoreProductDtoToProduct(productDto);
    }
    
    @Override
    public List<Product> getAllProduct() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate
                                                             .getForObject(
                                                                     "https://fakestoreapi.com/products",
                                                                     FakeStoreProductDto[].class
                                                             );
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return products;
    }
    
    
}
