package com.bci.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

	// Agrupa y documenta partes específicas de la API correspondiente user
	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("1_users").pathsToMatch("/api/users/**").build();
	}

	// Prueba de concepto para demostrar que aquí se documentaria otro endpoint
	@Bean
	public GroupedOpenApi productApi() {
		return GroupedOpenApi.builder().group("2_products").pathsToMatch("/api/products/**").build();
	}

}