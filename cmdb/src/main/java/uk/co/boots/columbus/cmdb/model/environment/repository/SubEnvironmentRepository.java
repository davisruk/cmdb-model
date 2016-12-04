package uk.co.boots.columbus.cmdb.model.environment.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment_;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;

public interface SubEnvironmentRepository extends JpaRepository<SubEnvironment, Long> {
	public SubEnvironment findOne(Long id);
	
	Page<SubEnvironment>findBySubEnvironmentType_nameContainsAndIdNotIn(Pageable pageRequest, String name, List<Long> idList);
	Page<SubEnvironment>findBySubEnvironmentType_IdNotIn(Pageable pageRequest, List<Long> idList);
	default List<SubEnvironment> complete(Long query, int maxResults) {
		SubEnvironment probe = new SubEnvironment();
		probe.setId(query);

		ExampleMatcher matcher = ExampleMatcher.matching() //
				.withMatcher(SubEnvironment_.id.getName(), match -> match.ignoreCase().contains());

		Page<SubEnvironment> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
		return page.getContent();
	}	
}
