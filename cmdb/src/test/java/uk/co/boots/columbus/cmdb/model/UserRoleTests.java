package uk.co.boots.columbus.cmdb.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.co.boots.columbus.cmdb.model.user.domain.Role;
import uk.co.boots.columbus.cmdb.model.user.domain.User;
import uk.co.boots.columbus.cmdb.model.user.dto.RoleDTO;
import uk.co.boots.columbus.cmdb.model.user.dto.RoleDTOService;
import uk.co.boots.columbus.cmdb.model.user.dto.UserDTO;
import uk.co.boots.columbus.cmdb.model.user.dto.UserDTOService;
import uk.co.boots.columbus.cmdb.model.user.repository.RoleRepository;
import uk.co.boots.columbus.cmdb.model.user.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRoleTests {

	@Configuration
	static class TestConfig {
		@Bean
		public UserDTOService userDTOService() {
			return new UserDTOService();
		}

		@Bean
		public RoleDTOService roleDTOService() {
			return new RoleDTOService();
		}

		@Bean(name = "passwordEncoder")
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
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
	public void testUserRoleEntityPersistence() throws Exception {
		// Create a new User
		User user = new User();
		user.setUserName("TestUser");
		user.setEmail("test@davisfamily.eu");
		user.setEnabled(true);
		user.setPassword("test");
		this.entityManager.persist(user);

		// Create a new Role
		Role role = new Role();
		role.setName("ROLE_TEST");
		this.entityManager.persist(role);

		// Add Role to User
		// Get the User and Role from the DB first
		User dbUser = this.userRepo.findByUserName("TestUser");
		List<Role> roles = new ArrayList<Role>();
		Role role2 = this.roleRepo.findByName("ROLE_TEST");
		roles.add(role2);
		dbUser.setRoles(roles);
		this.entityManager.persist(dbUser);

		// Get the User from the DB and retrieve roles through property not repo
		User dbUser2 = this.userRepo.findByUserName("TestUser");
		List<Role> roles2 = dbUser2.getRoles();

		assertThat(roles2, hasSize(1));
		assertThat((roles2.get(0)).getName(), is("ROLE_TEST"));

	}

	//@Test
	public void testUserServicePersistence() throws Exception {
		// Create a new User
		UserDTO userDTO = new UserDTO();
		userDTO.userName = "TestUser1";
		userDTO.email = "test1@davisfamily.eu";
		userDTO.enabled = true;
		userDTO.password = "test";
		// Persist the user
		userDTO = userDTOService.save(userDTO);
		assertNotNull(userDTO);
		assertTrue(userDTO.id > 0);

		// Create a new Role
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.name = "ROLE_TEST1";
		// Persist the role
		roleDTO = roleDTOService.save(roleDTO);
		assertNotNull(roleDTO);
		assertTrue(roleDTO.id > 0);

		// Add Role to User
		// Get the User and Role from the DB first
		userDTO = userDTOService.findOne(userDTO.id);
		roleDTO = roleDTOService.findOne(roleDTO.id);

		// Add the role to the User
		// We know the user has no roles
		List<RoleDTO> roles = new ArrayList<RoleDTO>();
		roles.add(roleDTO);
		userDTO.roles = roles;

		// Persist again to create the many:many row in USER_ROLE table
		userDTOService.save(userDTO);

		// Get the user from the DB again
		UserDTO dto2 = userDTOService.findOne(userDTO.id);
		// Roles should not be null and should contain an entry
		assertNotNull(dto2.roles);
		assertFalse(dto2.roles.isEmpty());
		assertThat(dto2.roles.get(0).name, is("ROLE_TEST1"));

	}
}