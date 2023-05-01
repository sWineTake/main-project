package com.playground.song.configuration.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
		info = @Info(title = "API 명세서", description = "playground API 명세서", version = "api.v1")
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
	@Bean
	public GroupedOpenApi chatOpenApi() {
		String[] paths = {"/v1/**"};

		return GroupedOpenApi.builder()
				.group("API.v1")
				.pathsToMatch(paths)
				.build();
	}

}
