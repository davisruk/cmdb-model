package uk.co.boots.columbus.cmdb.model.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import uk.co.boots.columbus.cmdb.model.core.domain.LockStrategy;
import uk.co.boots.columbus.cmdb.model.core.domain.OptimisticLockingStrategy;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public LockStrategy lockStrategy(){
		return new OptimisticLockingStrategy();
	}
}
