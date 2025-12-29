package com.service.inventory.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuración para agregar automáticamente el API Key
 * a todas las peticiones Feign hacia el servicio de productos.
 */
@Configuration
public class FeignConfig {

    @Value("${products.api-key}")
    private String productServiceApiKey;

    /**
     * Interceptor que agrega el header X-API-KEY automáticamente
     * a todas las peticiones Feign.
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            template.header("X-API-KEY", productServiceApiKey);
        };
    }
}
