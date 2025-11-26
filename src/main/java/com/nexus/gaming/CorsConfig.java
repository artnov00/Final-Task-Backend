package com.nexus.gaming; // Ajuste o pacote se necessário

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica a todas as rotas da API
                        .allowedOrigins("*") // Permite qualquer site (Vercel, Localhost, etc)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite todos os métodos
                        .allowedHeaders("*"); // Permite todos os cabeçalhos (Content-Type, etc)
            }
        };
    }
}