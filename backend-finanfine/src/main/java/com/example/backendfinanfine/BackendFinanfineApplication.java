package com.example.backendfinanfine;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "FinanFine API para gerenciar empréstimos",
                version = "1.0.0",
                description = "Desenvolvido por João Vidal")
)
public class BackendFinanfineApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendFinanfineApplication.class, args);
    }

}
