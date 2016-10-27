/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/repository/EntityRepository.java.e.vm
 */
package uk.co.boots.columbus.cmdb.model.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.*;

import uk.co.boots.columbus.cmdb.model.domain.EnvironmentConfig;
import uk.co.boots.columbus.cmdb.model.domain.EnvironmentConfig_;

public interface EnvironmentConfigRepository extends JpaRepository<EnvironmentConfig, Long> {

    default List<EnvironmentConfig> complete(String query, int maxResults) {
        EnvironmentConfig probe = new EnvironmentConfig();
        probe.setParameter(query);

        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(EnvironmentConfig_.parameter.getName(), match -> match.ignoreCase().startsWith());

        Page<EnvironmentConfig> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
        return page.getContent();
    }
}