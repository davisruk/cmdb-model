package uk.co.boots.columbus.cmdb.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import uk.co.boots.columbus.cmdb.model.domain.Role;
import uk.co.boots.columbus.cmdb.model.domain.User;
import uk.co.boots.columbus.cmdb.model.repository.RoleRepository;
import uk.co.boots.columbus.cmdb.model.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace=Replace.NONE)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    
    @Test
    public void testUserPersistence() throws Exception {
    	// Create a new User
    	User user = new User();
    	user.setUserName("rich");
    	user.setEmail("rich@davisfamily.eu");
    	user.setEnabled(true);
    	user.setPassword("test");
    	this.entityManager.persist(user);
    	
    	// Create a new Role
    	Role role = new Role();
    	role.setName("ROLE_ADMIN");
    	this.entityManager.persist(role);

        // Add Role to User
    	// Get the User and Role from the DB first
    	User dbUser = this.userRepo.findByUserName("rich");
        List<Role> roles = new ArrayList<Role>();
        Role role2 = this.roleRepo.findByName("ROLE_ADMIN");
        roles.add(role2);
        dbUser.setRoles(roles);
        this.entityManager.persist(dbUser);
        
        
        // Get the User from the DB and retrieve roles through property not repo
        User dbUser2 = this.userRepo.findByUserName("rich");
        List<Role> roles2 = dbUser2.getRoles();
        
        assertThat(roles2, hasSize(1));
        assertThat((roles2.get(0)).getName(), is("ROLE_ADMIN"));
    }
}