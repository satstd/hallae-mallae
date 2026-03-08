package com.java.hallaemallae.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {

    @Bean
    public OpenAPI springBoardOpenAPI() {
        return new OpenAPI().info(new Info().title("할래말래 Swagger")
                .description("할래말래 스웨거~")
                .version("v0.0.1")
                .license(new License().name("Github").url("https://github.com/satstd/hallae-mallae"))
        );
    }
}