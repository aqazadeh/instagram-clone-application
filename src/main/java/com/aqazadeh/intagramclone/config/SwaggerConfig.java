package com.aqazadeh.intagramclone.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 17.03.2024
 * Time: 04:09
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("Bearer Authentication"
                                )
                )
                .components(new Components().addSecuritySchemes(
                        "Bearer Authentication",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer")
                        )
                )
                .info(new Info()
                        .title("Instagram Rest Api")
                        .description("Rest api for using instagram")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Rovshan Aghayev")
                                .email("rovsenaqazadeh@gmail.com")
                                .url("https://aqazadeh.com")
                        )
                );
    }
}
