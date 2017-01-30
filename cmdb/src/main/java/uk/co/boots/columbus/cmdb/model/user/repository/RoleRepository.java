package uk.co.boots.columbus.cmdb.model.user.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.user.domain.Role;
import uk.co.boots.columbus.cmdb.model.user.domain.Role_;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	   
		Role findByName(String name);
		List<Role> findAll();
		/*
		@Query("select r from Role r where r.id not in"
				+ "(select ur.role_id from user_role ur where ur.id = :id)")
		List<Role> findNotAssignedToUser(@Param("id") Integer id);
		*/
		
		default List<Role> complete(String query, int maxResults) {
	        Role probe = new Role();
	        probe.setName(query);

	        ExampleMatcher matcher = ExampleMatcher.matching() //
	                .withMatcher(Role_.name.getName(), match -> match.ignoreCase().startsWith());


	        Page<Role> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
	        return page.getContent();
	    }

}
