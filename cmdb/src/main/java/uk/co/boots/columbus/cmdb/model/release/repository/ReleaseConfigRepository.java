package uk.co.boots.columbus.cmdb.model.release.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.release.domain.ReleaseConfig;
import uk.co.boots.columbus.cmdb.model.release.domain.ReleaseConfig_;

public interface ReleaseConfigRepository extends JpaRepository<ReleaseConfig, Long> {

    public List<ReleaseConfig> findByRelease_name(String name);

    public List<ReleaseConfig> findDistinctByRelease_subEnvironments_environment_name(String name);
    
    public List<ReleaseConfig> findByRecursiveByEnvAndRelease_subEnvironments_environment_name(Boolean repeatsByEnv, String name);
    public List<ReleaseConfig> findDistinctByRecursiveByEnvAndRecursiveBySubEnvAndRelease_subEnvironments_environment_id (Boolean repeatsByEnv, Boolean repeatsBySubEnv, Long envId);

    //public List<ReleaseConfig> findByRecursiveByEnvAndRelease_subEnvironments(Long id, Boolean recurseByEnv, Boolean recurseBySubEnv)
    default List<ReleaseConfig> complete(String query, int maxResults) {
        ReleaseConfig probe = new ReleaseConfig();
        probe.setParameter(query);

        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(ReleaseConfig_.parameter.getName(), match -> match.ignoreCase().startsWith());

        Page<ReleaseConfig> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
        return page.getContent();
    }


}
