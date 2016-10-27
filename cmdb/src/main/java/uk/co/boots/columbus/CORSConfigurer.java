package uk.co.boots.columbus;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CORSConfigurer extends WebMvcConfigurerAdapter{

	

	@Override
	public void addCorsMappings(CorsRegistry registry) {
 //       registry.addMapping("/api/authenticated/").allowedOrigins("http://localhost:3000");
 //       registry.addMapping("/api/login/").allowedOrigins("http://localhost:3000");

		addToRegistry("/api/*/", registry);
		addToRegistry("/api/servers/**", registry);
		addToRegistry("/api/environments/**", registry);
		addToRegistry("/api/packageInfos/**", registry);
		addToRegistry("/api/componentConfigs/**", registry);
		addToRegistry("/api/globalconfigs/**", registry);
		addToRegistry("/api/packageTypes/**", registry);
		addToRegistry("/api/environmentConfigs/**", registry);
		addToRegistry("/api/serverConfigs/**", registry);
		addToRegistry("/api/solutionComponents/**", registry);
		addToRegistry("/api/releaseDatas/**", registry);
		addToRegistry("/api/releaseDataTypes/**", registry);
		addToRegistry("/api/serverTypes/**", registry);
		addToRegistry("/api/releases/**", registry);
		super.addCorsMappings(registry);

	}

	private void addToRegistry(String patternPath, CorsRegistry registry){
		registry.addMapping(patternPath)
		.allowedOrigins("*")
		.allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
		.allowedHeaders("Content-Type", "Accept", "X-Requested-With", "remember-me")
		.allowCredentials(true).maxAge(3600);		
	}
}
