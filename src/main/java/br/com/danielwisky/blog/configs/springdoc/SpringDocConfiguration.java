package br.com.danielwisky.blog.configs.springdoc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Blog", version = "v1.0.0", description = "Documentation APIs v1.0.0"))
public class SpringDocConfiguration {

}
