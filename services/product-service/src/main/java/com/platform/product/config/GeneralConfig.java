package com.platform.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GeneralConfig {
    
    @Bean
    public RestTemplate createRestTemplate() {
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        factory.setConnectTimeout(1000);
//        factory.setReadTimeout(5000);
        return new RestTemplate();
    }
    
    @Bean
    public RestClient createRestClient(){
        return RestClient.create();
    }
}
