package br.com.ironmukeka.favorecidos.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket baseDeFavorecidosApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.ironmukeka.favorecidos"))
				.paths(PathSelectors.ant("/**"))
				.build();
	}

	public ApiInfo apiInfo(){
		return new ApiInfoBuilder()
				.title("Base de Cadastro de Favorecidos")
				.description("Projeto simples para simular um cadastro de favorecido.")
				.version("v0.0.0")
				.build();
	}
}
