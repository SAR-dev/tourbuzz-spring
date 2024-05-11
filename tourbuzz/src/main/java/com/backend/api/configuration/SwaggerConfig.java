package com.backend.api.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@SecurityScheme(
        name = "apiKeyAuth",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        paramName = "X-API-KEY"
)
@OpenAPIDefinition(
        info = @Info(
                title = "Guest or Public API",
                version = "v1"
        ),
        security = {
                @SecurityRequirement(name = "basicAuth"),
                @SecurityRequirement(name = "apiKeyAuth")
        }
)
public class SwaggerConfig {
        @Value("${bezkoder.openapi.dev-url}")
        private String devUrl;

        @Value("${bezkoder.openapi.prod-url}")
        private String prodUrl;

        @Bean
        public OpenAPI myOpenAPI() {
                Server devServer = new Server();
                devServer.setUrl(devUrl);
                devServer.setDescription("Server URL in Development environment");

                Server prodServer = new Server();
                prodServer.setUrl(prodUrl);
                prodServer.setDescription("Server URL in Production environment");

                Contact contact = new Contact();
                contact.setEmail("bezkoder@gmail.com");
                contact.setName("BezKoder");
                contact.setUrl("https://www.bezkoder.com");

                return new OpenAPI().servers(List.of(devServer, prodServer));
        }
}