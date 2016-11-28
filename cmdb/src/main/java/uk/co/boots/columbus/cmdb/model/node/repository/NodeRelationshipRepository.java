package uk.co.boots.columbus.cmdb.model.node.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.node.domain.NodeRelationship;
import uk.co.boots.columbus.cmdb.model.node.domain.NodeRelationship_;

public interface NodeRelationshipRepository  extends JpaRepository<NodeRelationship, Long> {

    /**
     * Return the persistent instance of {@link NodeRelationship} with the given unique property value name,
     * or null if there is no such persistent instance.
     *
     * @param name the unique value
     * @return the corresponding {@link NodeRelationship} persistent instance or null
     */
    NodeRelationship getById(Long id);

    default List<NodeRelationship> complete(Long id, int maxResults) {
        NodeRelationship probe = new NodeRelationship();
        probe.setId(id);

        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(NodeRelationship_.id.getName(), match -> match.ignoreCase().startsWith());

        Page<NodeRelationship> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
        return page.getContent();
    }
}
