/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/repository/EntityRepository.java.e.vm
 */
package uk.co.boots.columbus.cmdb.model.release.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.*;

import uk.co.boots.columbus.cmdb.model.release.domain.Release;
import uk.co.boots.columbus.cmdb.model.release.domain.Release_;

public interface ReleaseRepository extends JpaRepository<Release, Long> {

    /**
     * Return the persistent instance of {@link Release} with the given unique property value name,
     * or null if there is no such persistent instance.
     *
     * @param name the unique value
     * @return the corresponding {@link Release} persistent instance or null
     */
    Release getByName(String name);
    Release findBysubEnvironments_Environment_idAndSubEnvironments_subEnvironmentType_id(Long envId, Long subEnvTypeId);
    default List<Release> complete(String query, int maxResults) {
        Release probe = new Release();
        probe.setName(query);

        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(Release_.name.getName(), match -> match.ignoreCase().startsWith());

        Page<Release> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
        return page.getContent();
    }
}