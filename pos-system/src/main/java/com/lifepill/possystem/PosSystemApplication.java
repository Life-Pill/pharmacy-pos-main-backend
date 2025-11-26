package com.lifepill.possystem;

import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		info = @Info(
				title = "LifePill POS -System API Documentation",
				description = "LIFEPILL",
				version = "v1",
				contact = @Contact(
						name = "LifePIll",
						email = "lifepillinfo@gmail.com",
						url = "https://github.com/Life-Pill"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/Life-Pill"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "LifePill POS System REST API Documentation",
				url = "http://localhost:8081/swagger-ui/index.html#/"
		)
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl") // Enable JPA Auditing
@Log4j2
@EnableCaching
public class PosSystemApplication {

	/**
	 * Entry point of the application.
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		SpringApplication.run(PosSystemApplication.class, args);
	}

}

