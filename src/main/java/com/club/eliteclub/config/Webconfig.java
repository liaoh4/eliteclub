package com.club.eliteclub.config;

import com.club.eliteclub.initializers.Forbes400Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Webconfig {
    @Autowired
    Forbes400Properties forbes400Properties;
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                // 进行其他配置
                .baseUrl(forbes400Properties.buildEndPoint());
    }

    @Bean
    public WebClient webClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.build();
    }
}
