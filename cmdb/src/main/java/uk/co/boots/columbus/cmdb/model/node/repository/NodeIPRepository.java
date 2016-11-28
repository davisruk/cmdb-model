package uk.co.boots.columbus.cmdb.model.node.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.node.domain.NodeIP;
import uk.co.boots.columbus.cmdb.model.node.domain.NodeIP_;

public interface NodeIPRepository  extends JpaRepository<NodeIP, Long> {

    /**
     * Return the persistent instance of {@link NodeIP} with the given unique property value name,
     * or null if there is no such persistent instance.
     *
     * @param name the unique value
     * @return the corresponding {@link NodeIP} persistent instance or null
     */
    NodeIP getById(Long id);

    default List<NodeIP> complete(String query, int maxResults) {
        NodeIP probe = new NodeIP();
        probe.setIpAddress(query);

        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(NodeIP_.ipAddress.getName(), match -> match.ignoreCase().startsWith());

        Page<NodeIP> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
        return page.getContent();
    }
}
