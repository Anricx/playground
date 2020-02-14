package com.github.anricx.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "My API", version = "1.0.0", description = "My API REST API",
                contact = @Contact(url = "https://github.com/Anricx", name = "Developer", email = "joe.dengtao@gmail.com")
        )
)
@Configuration
public class SpringConfig {

}
