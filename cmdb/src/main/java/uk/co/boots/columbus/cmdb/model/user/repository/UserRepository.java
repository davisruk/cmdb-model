package uk.co.boots.columbus.cmdb.model.user.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.user.domain.User;
import uk.co.boots.columbus.cmdb.model.user.domain.User_;

public interface UserRepository extends JpaRepository<User, Integer> {
	   
		User findByUserName(String userName);
		
		default List<User> complete(String query, int maxResults) {
	        User probe = new User();
	        probe.setUserName(query);

	        ExampleMatcher matcher = ExampleMatcher.matching() //
	                .withMatcher(User_.userName.getName(), match -> match.ignoreCase().startsWith());

	        Page<User> page = findAll(Example.of(probe, matcher), new PageRequest(0, maxResults));
	        return page.getContent();
	    }
}
