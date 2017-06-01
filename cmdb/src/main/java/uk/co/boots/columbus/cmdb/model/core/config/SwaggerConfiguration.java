package uk.co.boots.columbus.cmdb.model.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

	@Bean
	public Docket swaggerSpringfoxDocket() {
		log.debug("Starting Swagger");
		StopWatch watch = new StopWatch();
		watch.start();
		Docket docket = new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().pathMapping("/").genericModelSubstitutes(ResponseEntity.class);
		watch.stop();
		log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
		return docket;
	}

	// Security Bean for Swagger
	// Configures the Swagger to insert an Authorization key in the header for each HTTP request.
	// The value token that is added to the Authorization header is set in Swagger UI.
	// Spring Security Configuration needs to allow requests through for
	// /swagger-resources/configuration/security. Swagger UI will then present
	// a text box next to the Explore button that can be used to set the token.
	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration(null, null, null, null, "Access Token", ApiKeyVehicle.HEADER,
				"Authorization", ",");
	}
}
