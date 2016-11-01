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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.co.boots.columbus.cmdb.model.domain.Role;
import uk.co.boots.columbus.cmdb.model.domain.User;
import uk.co.boots.columbus.cmdb.model.dto.RoleDTO;
import uk.co.boots.columbus.cmdb.model.dto.RoleDTOService;
import uk.co.boots.columbus.cmdb.model.dto.UserDTO;
import uk.co.boots.columbus.cmdb.model.dto.UserDTOService;
import uk.co.boots.columbus.cmdb.model.repository.RoleRepository;
import uk.co.boots.columbus.cmdb.model.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RepositoryTests {

	@Configuration
	static class TestConfig {
       @Bean
       public UserDTOService userDTOService() {
        return new UserDTOService();
       }

       @Bean
       public RoleDTOService roleDTOService(){
    	   return new RoleDTOService();
       }
    }

	@Autowired
    RoleDTOService roleDTOService;

	@Autowired
    UserDTOService userDTOService;

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
    
	 @Test
	 public void testServices() throws Exception {
	    	// Create a new User
	    	UserDTO userDTO = new UserDTO();
	    	userDTO.userName = "rich1";
	    	userDTO.email = "rich@davisfamily.eu";
	    	userDTO.enabled = true;
	    	userDTO.password = "test";
	    	userDTO = userDTOService.save(userDTO);
	    	System.out.println(userDTO);
	    	// Create a new Role
	    	RoleDTO roleDTO = new RoleDTO();
	    	roleDTO.name = "ROLE_TEST";
	    	roleDTO = roleDTOService.save(roleDTO);;
	    	System.out.println(roleDTO);
	        // Add Role to User
	    	// Get the User and Role from the DB first
	    	userDTO = userDTOService.findOne(userDTO.id);
	        roleDTO = roleDTOService.findOne(roleDTO.id);
	        List<RoleDTO> roles = new ArrayList<RoleDTO>();
	        roles.add(roleDTO);
	        userDTO.roles = roles;
	        roleDTO.addUser(userDTO);
	        List<UserDTO> users = new ArrayList<UserDTO>();
	        roleDTO.users = users;
	        userDTO = userDTOService.save(userDTO);
	        roleDTO = roleDTOService.save(roleDTO);
	        System.out.println(userDTO);
	        UserDTO dto2 = userDTOService.findOne(2);
	        System.out.println(dto2);
	 }
}