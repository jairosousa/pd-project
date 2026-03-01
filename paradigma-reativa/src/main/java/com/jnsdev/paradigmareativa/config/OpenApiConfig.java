package com.jnsdev.paradigmareativa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Autor Jairo Nascimento
 * @Created 01/03/2026 - 17:05
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Pagamentos Reativa")
                        .version("1.0")
                        .description("Implementação do Strategy Pattern com WebFlux e OpenAPI"))
                .addServersItem(new Server().url("http://localhost:8080").description("Ambiente de desenvolvimento"));
    }
}
