package uk.co.boots.columbus.cmdb.model.server.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.server.domain.Server;
import uk.co.boots.columbus.cmdb.model.server.domain.Server_;

public interface ServerRepository extends JpaRepository<Server, Long> {

    /**
     * Return the persistent instance of {@link Server} with the given unique property value name,
     * or null if there is no such persistent instance.
     *
     * @param name the unique value
     * @return the corresponding {@link Server} persistent instance or null
     */
    Server findByName(String name);
    
    List<Server>findByIdNotIn(List<Long> idList);
    
    Page<Server>findByNameContainsAndServerType_nameContainsAndIdNotIn(Pageable pageRequest, String name, String type, List<Long> idList);
    Page<Server>findByNameContainsAndIdNotIn(Pageable pageRequest, String name, List<Long> idList);
    Page<Server>findByServerType_nameContainsAndIdNotIn(Pageable pageRequest, String type, List<Long> idList);
    Page<Server>findByIdNotIn(Pageable pageRequest, List<Long> idList);
    
    default List<Server> complete(String query, int maxResults) {
        Server probe = new Server();
        probe.setName(query);

        ExampleMatcher matcher = ExampleMatcher.matching() //
                .withMatcher(Server_.name.getName(), match -> match.ignoreCase().startsWith());

        Page<Server> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
        return page.getContent();
    }
}