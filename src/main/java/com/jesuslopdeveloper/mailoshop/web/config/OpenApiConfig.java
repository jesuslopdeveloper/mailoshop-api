package com.jesuslopdeveloper.mailoshop.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI ConfSwagger() {
        Contact contacto = new Contact();
        contacto.setEmail("jesus.ant.lopez.reyes@gmail.com");
        contacto.setName("MailoShop Api");

        License licencia = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Documentación MailoShop Api")
                .version("1.0")
                .contact(contacto)
                .description("Esta es una api de ejemplo con Spring Framework y PostgreSQL")
                .license(licencia);
        return new OpenAPI().info(info);
    }
}
