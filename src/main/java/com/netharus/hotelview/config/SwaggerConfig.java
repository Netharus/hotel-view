package com.netharus.hotelview.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Mikhail Kondratsiuk",
                        email = "nekromant12331@gmail.com"
                ),
                title = "OpenApi specification - Hotel View",
                description = "OpenApi docs for Hotel View Project",
                version = "1.0"
        )
)
public class SwaggerConfig {
}
