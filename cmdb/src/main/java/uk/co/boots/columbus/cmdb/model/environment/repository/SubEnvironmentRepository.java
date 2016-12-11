package uk.co.boots.columbus.cmdb.model.environment.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment_;

public interface SubEnvironmentRepository extends JpaRepository<SubEnvironment, Long> {
	public SubEnvironment findOne(Long id);
	
	Page<SubEnvironment>findBySubEnvironmentType_nameContainsAndIdNotIn(Pageable pageRequest, String name, List<Long> idList);
	Page<SubEnvironment>findBySubEnvironmentType_IdNotIn(Pageable pageRequest, List<Long> idList);
	
	// have to use both of below in conjunction as the not in subselect is not working
	@Query("select nse.subEnvironment from NodeSubEnvironment nse where nse.node.id = :id")
	List<SubEnvironment>findSubEnvsOfServer(@Param("id") Long serversNodeId);
	List<SubEnvironment>findByIdNotIn(List<Long> subEnvironmentIds);
	
	// below doesn't work - sub select generates invalid select but value inner joins and where
	//@Query("SELECT se FROM SubEnvironment se WHERE se not in (select s.subEnvironments from Server s where s.id = :id)")
	//List<SubEnvironment>findSubEnvsWithoutServer(@Param("id") Long id);

	default List<SubEnvironment> complete(Long query, int maxResults) {
		SubEnvironment probe = new SubEnvironment();
		probe.setId(query);

		ExampleMatcher matcher = ExampleMatcher.matching() //
				.withMatcher(SubEnvironment_.id.getName(), match -> match.ignoreCase().contains());

		Page<SubEnvironment> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
		return page.getContent();
	}	
}
