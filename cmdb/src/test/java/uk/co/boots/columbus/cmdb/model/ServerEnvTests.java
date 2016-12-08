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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.co.boots.columbus.cmdb.model.environment.domain.Environment;
import uk.co.boots.columbus.cmdb.model.environment.domain.EnvironmentType;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironmentType;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.environment.repository.SubEnvironmentRepository;
import uk.co.boots.columbus.cmdb.model.node.domain.IPType;
import uk.co.boots.columbus.cmdb.model.node.domain.Node;
import uk.co.boots.columbus.cmdb.model.node.domain.NodeIP;
import uk.co.boots.columbus.cmdb.model.node.domain.NodeType;
import uk.co.boots.columbus.cmdb.model.node.repository.NodeRepository;
import uk.co.boots.columbus.cmdb.model.release.domain.Release;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseDTO;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseDTOService;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;
import uk.co.boots.columbus.cmdb.model.server.domain.ServerConfig;
import uk.co.boots.columbus.cmdb.model.server.domain.ServerType;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerConfigDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerConfigDTOService;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTOService;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerTypeDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerTypeDTOService;
import uk.co.boots.columbus.cmdb.model.server.repository.ServerConfigRepository;
import uk.co.boots.columbus.cmdb.model.server.repository.ServerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
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

	@Autowired
	private NodeRepository nRepo;

	@Autowired
	private SubEnvironmentRepository seRepo;

	//@Test
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
		this.entityManager.persist(e);
		List<Environment> envs = new ArrayList<Environment>();
		envs.add(e);
//		s.setEnvironments(envs);
		this.entityManager.persist(s);
		Server s1 = this.serverRepo.findByName("TestServer");
/*
		List<Environment> envs1 = s1.getEnvironments();
		System.out.println(s1);
		System.out.println(envs1.get(0));
		assertThat(envs1, hasSize(1));
		assertThat((envs1.get(0)).getName(), is("Test Environment"));
*/
		// try and get a config for a server that is in a particular environment
		ServerConfig sc = new ServerConfig();
		sc.setHieraAddress("HIERA_ADDRESS");
		sc.setParameter("Parameter");
		sc.setValue("Value");
		sc.setServer(s);
		this.entityManager.persist(sc);
		List<ServerConfig> scl = scRepo.findByServer_subEnvironments_environment_name("Test Environment");
		assertNotNull(scl);
		assertThat(scl, hasSize(1));
		System.out.println(scl.get(0));
	}

	//@Test
	public void testServerEnvironmentServicePersistence() throws Exception {
		ServerTypeDTO st = new ServerTypeDTO();
		st.name = "Test Server Type";
		st = stService.save(st);
		ServerDTO s = new ServerDTO();
		s.name = "TestServer";
		s.serverType = st;
		s = sService.save(s);
		ReleaseDTO r = new ReleaseDTO();
		r.name = "TEST_RELEASE";
		r = rService.save(r);
		EnvironmentDTO e = new EnvironmentDTO();
		e.name = "Test Environment";
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

		List<ServerConfigDTO> scl = scService.findByServerSubEnvironmentName("Test Environment");
		assertNotNull(scl);
		assertThat(scl, hasSize(1));
		System.out.println(scl.get(0));

	}
	
	//@Test
	public void testServerNotIn() throws Exception {
		ArrayList<ServerDTO> slist = new ArrayList<ServerDTO>();
		ServerTypeDTO st = new ServerTypeDTO();
		st.name = "Test Server Type";
		st = stService.save(st);
		ServerDTO s = new ServerDTO();
		s.name = "TestServer";
		s.serverType = st;
		s = sService.save(s);
		ReleaseDTO r = new ReleaseDTO();
		r.name = "TEST_RELEASE";
		r = rService.save(r);
		EnvironmentDTO e = new EnvironmentDTO();
		e.name = "Test Environment";
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
		
		ArrayList<Long> al= new ArrayList<Long>();
		al.add(s.id);
		PageRequest pr = new PageRequest(0,10);
		Page<Server> serverPage = serverRepo.findByNameContainsAndIdNotIn(pr, "_Two", al);
		List<Server> servers = serverPage.getContent();
		System.out.println(servers.get(0).getName());
		assertThat(servers, hasSize(1));

		servers = serverRepo.findByNameContainsAndServerType_nameContainsAndIdNotIn(pr, "_Two", "Serv", al).getContent();
		System.out.println(servers.get(0).getName());
		assertThat(servers, hasSize(1));
		
	}
	
	//@Test
	public void testServerSubEnvironmentEntityPersistence() throws Exception {
		ServerType st = new ServerType();
		st.setName("Test Server Type");
		this.entityManager.persist(st);
		Server s = new Server();
		s.setName("TestServer");
		s.setServerType(st);
		this.entityManager.persist(s);
		Release r = new Release();
		r.setName("TEST_RELEASE");
		this.entityManager.persist(r);
		Environment e = new Environment();
		e.setName("Test Environment");
		EnvironmentType et = new EnvironmentType();
		et.setName("PRODUCTION");
		this.entityManager.persist(et);
		e.setEnvironmentType(et);
		this.entityManager.persist(e);

		SubEnvironmentType set = new SubEnvironmentType();
		set.setName("TestEnvType");
		this.entityManager.persist(set);
		SubEnvironment se = new SubEnvironment();
		se.setEnvironment(e);
		se.setRelease(r);
		se.setSubEnvironmentType(set);
		List<Server> servers = new ArrayList<Server>();
		servers.add(s);
		//se.setServers(servers);
		this.entityManager.persist(se);


		// try and get a config for a server that is in a particular subenvironment
		ServerConfig sc = new ServerConfig();
		sc.setHieraAddress("HIERA_ADDRESS");
		sc.setParameter("Parameter");
		sc.setValue("Value");
		sc.setServer(s);
		this.entityManager.persist(sc);
		//List<ServerConfig> scl = scRepo.findByServer_subEnvironments_subEnvironmentType_name("TestEnvType");
		//List<ServerConfig> scl = scRepo.findByServer_subEnvironments_id(1L);
		//List<ServerConfig> scl = scRepo.findByServer_subEnvironments_release_name("TEST_RELEASE");
		
		//assertNotNull(scl);
		//assertThat(scl, hasSize(1));
		//System.out.println(scl.get(0));
	}
	
	@Test
	public void testNodeEntityPersistence() throws Exception {
		ServerType st = new ServerType("TEST_SERVERTYPE");
		this.entityManager.persist(st);
		Server s =  new Server("TEST_SERVER_1", st);
		this.entityManager.persist(s);
		Release r = createRelease("TEST_RELEASE");
		this.entityManager.persist(r);
		SubEnvironmentType set = createSubEnvType("TEST_SUB_ENV");		
		this.entityManager.persist(set);
		EnvironmentType et = createEnvType("TEST_ENV_TYPE");
		this.entityManager.persist(et);
		Environment e = createEnv(et);
		this.entityManager.persist(e);
		SubEnvironment se = createSubEnv(r, set, e);
		this.entityManager.persist(se);
		se.addNode(s, false);
		s.addSubEnvironment(se);
		this.entityManager.persist(se);
		this.entityManager.persist(s);
		List<Server> servers = serverRepo.findBySubEnvironments_id(1L);
		
		System.out.println(servers.size());
		ServerConfig sc = new ServerConfig();
		sc.setHieraAddress("HIERA_ADDRESS");
		sc.setParameter("Parameter");
		sc.setValue("Value");
		sc.setServer(servers.get(0));
		this.entityManager.persist(sc);
		List<ServerConfig> scl = scRepo.findByServer_name(servers.get(0).getName());
		System.out.println(scl.size());
	}

	private SubEnvironment createSubEnv (Release r, SubEnvironmentType set, Environment e){
		SubEnvironment se = new SubEnvironment();
		se.setEnvironment(e);
		se.setRelease(r);
		se.setSubEnvironmentType(set);
		return se;
	}
	private Release createRelease (String name){
		Release r = new Release();
		r.setName(name);
		return r;
	}
	
	private Environment createEnv (EnvironmentType et){
		Environment e = new Environment();
		e.setName("Test Environment");
		e.setEnvironmentType(et);
		return e;
	}
	
	private EnvironmentType createEnvType (String name){
		EnvironmentType et = new EnvironmentType();
		et.setName(name);
		return et;
	}

	private SubEnvironmentType createSubEnvType (String name){
		SubEnvironmentType set = new SubEnvironmentType();
		set.setName(name);
		return set;
	}

}