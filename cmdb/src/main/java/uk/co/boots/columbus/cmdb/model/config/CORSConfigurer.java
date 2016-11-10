package uk.co.boots.columbus.cmdb.model.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CORSConfigurer extends WebMvcConfigurerAdapter{

	
	@Value("${jwt.header}")
	private String authHeader; 

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		addToRegistry("/**/*", registry);
		super.addCorsMappings(registry);

	}

	private void addToRegistry(String patternPath, CorsRegistry registry){
		registry.addMapping(patternPath)
		.allowedOrigins("http://localhost:3000")
		.allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
		.allowedHeaders("Content-Type", "Accept", "X-Requested-With", "remember-me", authHeader)
		.allowCredentials(true).maxAge(3600);		
	}
}
