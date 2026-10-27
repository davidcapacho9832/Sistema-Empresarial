package com.tienda.usuarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * Configuracion de la documentacion OpenAPI / Swagger.
 * La interfaz queda disponible en http://localhost:8096/swagger-ui/index.html
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI usuarioApiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - Usuarios TiendaGenerica")
                        .description("Microservicio CRUD para la gestion de usuarios, con validaciones, "
                                + "cifrado de contrasenas y manejo centralizado de excepciones. "
                                + "Proyecto de la asignatura Desarrollo de Aplicaciones Empresariales.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Hermes David Capacho")));
    }
}
