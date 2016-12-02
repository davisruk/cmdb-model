package uk.co.boots.columbus.cmdb.model.environment.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.environment.domain.EnvironmentType;
import uk.co.boots.columbus.cmdb.model.environment.domain.EnvironmentType_;

public interface EnvironmentTypeRepository extends JpaRepository<EnvironmentType, Long> {

	default List<EnvironmentType> complete(String query, int maxResults) {
		EnvironmentType probe = new EnvironmentType();
		probe.setName(query);

		ExampleMatcher matcher = ExampleMatcher.matching() //
				.withMatcher(EnvironmentType_.name.getName(), match -> match.ignoreCase().contains());

		Page<EnvironmentType> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
		return page.getContent();
	}

}
