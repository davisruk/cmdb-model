package uk.co.boots.columbus.cmdb.model.environment.rest;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.environment.domain.EnvironmentConfig_;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironmentConfig;

public interface SubEnvironmentConfigRepository extends JpaRepository<SubEnvironmentConfig, Long>{

	public List<SubEnvironmentConfig> findBySubEnvironment_id(Long id);

    default List<SubEnvironmentConfig> complete(String query, int maxResults) {
        SubEnvironmentConfig probe = new SubEnvironmentConfig();
        probe.setParameter(query);

        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(EnvironmentConfig_.parameter.getName(), match -> match.ignoreCase().startsWith());

        Page<SubEnvironmentConfig> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
        return page.getContent();
    }
}
