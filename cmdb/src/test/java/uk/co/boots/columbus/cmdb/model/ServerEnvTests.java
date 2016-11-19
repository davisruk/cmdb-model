package uk.co.boots.columbus.cmdb.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
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

import uk.co.boots.columbus.cmdb.model.domain.Environment;
import uk.co.boots.columbus.cmdb.model.domain.Release;
import uk.co.boots.columbus.cmdb.model.domain.Server;
import uk.co.boots.columbus.cmdb.model.domain.ServerConfig;
import uk.co.boots.columbus.cmdb.model.domain.ServerType;
import uk.co.boots.columbus.cmdb.model.dto.EnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.dto.EnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.dto.ReleaseDTO;
import uk.co.boots.columbus.cmdb.model.dto.ReleaseDTOService;
import uk.co.boots.columbus.cmdb.model.dto.ServerConfigDTO;
import uk.co.boots.columbus.cmdb.model.dto.ServerConfigDTOService;
import uk.co.boots.columbus.cmdb.model.dto.ServerDTO;
import uk.co.boots.columbus.cmdb.model.dto.ServerDTOService;
import uk.co.boots.columbus.cmdb.model.dto.ServerTypeDTO;
import uk.co.boots.columbus.cmdb.model.dto.ServerTypeDTOService;
import uk.co.boots.columbus.cmdb.model.repository.ServerConfigRepository;
import uk.co.boots.columbus.cmdb.model.repository.ServerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ServerEnvTests {

	@Configuration
	static class TestConfig {

		@Bean
		public ServerConfigDTOService scService() {
			return new ServerConfigDTOService();
		}

		@Bean
		public ServerTypeDTOService stService() {
			return new ServerTypeDTOService();
		}

		@Bean
		public EnvironmentDTOService eService() {
			return new EnvironmentDTOService();
		}

		@Bean
		public ReleaseDTOService rService() {
			return new ReleaseDTOService();
		}

		@Bean
		public ServerDTOService sService() {
			return new ServerDTOService();
		}
	}
	
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private ServerRepository serverRepo;
	@Autowired
	private ServerConfigRepository scRepo;
	@Autowired
	private ServerConfigDTOService scService;
	@Autowired
	private ServerTypeDTOService stService;
	@Autowired
	private EnvironmentDTOService eService;
	@Autowired
	private ReleaseDTOService rService;
	@Autowired
	private ServerDTOService sService;

	@Test
	public void testServerEnvironmentEntityPersistence() throws Exception {
		ServerType st = new ServerType();
		st.setName("Test Server Type");
		this.entityManager.persist(st);
		Server s = new Server();
		s.setName("TestServer");
		s.setServerType(st);
		Release r = new Release();
		r.setName("TEST_RELEASE");
		this.entityManager.persist(r);
		Environment e = new Environment();
		e.setName("Test Environment");
		e.setRelease(r);
		this.entityManager.persist(e);
		List<Environment> envs = new ArrayList<Environment>();
		envs.add(e);
		s.setEnvironments(envs);
		this.entityManager.persist(s);
		Server s1 = this.serverRepo.findByName("TestServer");
		List<Environment> envs1 = s1.getEnvironments();
		System.out.println(s1);
		System.out.println(envs1.get(0));
		assertThat(envs1, hasSize(1));
		assertThat((envs1.get(0)).getName(), is("Test Environment"));

		// try and get a config for a server that is in a particular environment
		ServerConfig sc = new ServerConfig();
		sc.setHieraAddress("HIERA_ADDRESS");
		sc.setParameter("Parameter");
		sc.setValue("Value");
		sc.setServer(s);
		this.entityManager.persist(sc);
		List<ServerConfig> scl = scRepo.findByServer_environments_name("Test Environment");
		assertNotNull(scl);
		assertThat(scl, hasSize(1));
		System.out.println(scl.get(0));
	}

	@Test
	public void testServerEnvironmentServicePersistence() throws Exception {
		ServerTypeDTO st = new ServerTypeDTO();
		st.name = "Test Server Type";
		st = stService.save(st);
		ServerDTO s = new ServerDTO();
		s.name = "TestServer";
		s.serverType = st;
		ReleaseDTO r = new ReleaseDTO();
		r.name = "TEST_RELEASE";
		r = rService.save(r);
		EnvironmentDTO e = new EnvironmentDTO();
		e.name = "Test Environment";
		e.release = r;
		e = eService.save(e);
		List<EnvironmentDTO> envs = new ArrayList<EnvironmentDTO>();
		envs.add(e);
		s.environments = envs;
		s = sService.save(s);

		ServerDTO s1 = sService.findOne(s.id);
		List<EnvironmentDTO> envs1 = s1.environments;
		System.out.println(s1);
		System.out.println(envs1.get(0));
		assertThat(envs1, hasSize(1));
		assertThat((envs1.get(0)).name, is("Test Environment"));

		// try and get a config for a server that is in a particular environment
		ServerConfigDTO sc = new ServerConfigDTO();
		sc.hieraAddress = "HIERA_ADDRESS";
		sc.parameter = "Parameter";
		sc.value = "Value";
		sc.server = s;
		sc = scService.save(sc);

		List<ServerConfigDTO> scl = scService.findByServerEnvironmentName("Test Environment");
		assertNotNull(scl);
		assertThat(scl, hasSize(1));
		System.out.println(scl.get(0));

	}
	
	@Test
	public void testServerNotIn() throws Exception {
		ArrayList<ServerDTO> slist = new ArrayList<ServerDTO>();
		ServerTypeDTO st = new ServerTypeDTO();
		st.name = "Test Server Type";
		st = stService.save(st);
		ServerDTO s = new ServerDTO();
		s.name = "TestServer";
		s.serverType = st;
		ReleaseDTO r = new ReleaseDTO();
		r.name = "TEST_RELEASE";
		r = rService.save(r);
		EnvironmentDTO e = new EnvironmentDTO();
		e.name = "Test Environment";
		e.release = r;
		e = eService.save(e);
		List<EnvironmentDTO> envs = new ArrayList<EnvironmentDTO>();
		envs.add(e);
		s.environments = envs;
		s = sService.save(s);

		ServerDTO s1 = new ServerDTO();
		s1.name = "TestServer_Two";
		s1.serverType = st;
		s1 = sService.save(s1);
		slist.add(s);
		List<ServerDTO> dtolist = sService.getServersNotInList(slist);
		System.out.println(dtolist.get(0).name);
		assertThat(dtolist, hasSize(1));
	}	
}