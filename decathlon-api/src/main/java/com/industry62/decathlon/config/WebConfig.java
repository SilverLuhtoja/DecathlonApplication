// package com.industry62.decathlon.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// import jakarta.validation.constraints.NotNull;

// @Configuration
// public class WebConfig {
//     @Bean
//     public WebMvcConfigurer corsConfigurer() {
//         return new WebMvcConfigurer() {
//             @Override
//             public void addCorsMappings(@NotNull CorsRegistry registry) {
//                 registry.addMapping("/api")  // Adjust this to match your endpoint
//                         .allowedOrigins("http://localhost:5173", "http://127.0.0.1:5173") // Vue dev server URL
//                         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                         .allowedHeaders("*");
//             }
//         };
//     }
// }
