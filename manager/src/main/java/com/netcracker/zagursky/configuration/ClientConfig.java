package com.netcracker.zagursky.configuration;

import com.netcracker.zagursky.entity.catalog.Offer;
import com.netcracker.zagursky.entity.inventory.Order;
import jdk.internal.org.objectweb.asm.TypeReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Dzenyaa on 11.12.2017.
 */
@Configuration
public class ClientConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Accept", "application/json;charset=utf-8");
        return headers;
    }


}
