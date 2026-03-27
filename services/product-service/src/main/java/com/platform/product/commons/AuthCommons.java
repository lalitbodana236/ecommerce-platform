package com.platform.product.commons;

import com.platform.product.dtos.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    
    private final RestTemplate restTemplate;
    
    private String USER_SERVICE_URL = "http://localhost:8082/users/validate/";
    
    public AuthCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    
    public boolean validateToken(String tokenValue) {
        //call user-service to validate the token
        String url = USER_SERVICE_URL + tokenValue;
        UserDto userDto = restTemplate.getForObject(url, UserDto.class);
        if (userDto == null) return false;
        
        return true;
    }
}
