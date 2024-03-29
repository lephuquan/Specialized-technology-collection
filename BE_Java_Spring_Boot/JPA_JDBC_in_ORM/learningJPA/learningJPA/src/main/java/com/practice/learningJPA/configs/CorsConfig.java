package com.practice.learningJPA.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {// cho phép điạ chỉ truy cập tài nguyên server-BE
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:8080"
//                                ,
//                                "http://3.1.254.29:5000",
//                                "https://evsis-admindev.lotte.vn",
//                                "http://3.1.254.29:4000",
//                                "https://evsis-dev.lotte.vn/"
                                )
                        .allowCredentials(true)
                        .allowedHeaders("*", "Access-Control-Expose-Headers")
                        .allowedMethods("*");
            }
        };
    }
}
