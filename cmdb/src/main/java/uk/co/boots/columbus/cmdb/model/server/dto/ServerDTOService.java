package uk.co.boots.columbus.cmdb.model.server.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.core.dto.support.FilterMetadata;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.environment.repository.SubEnvironmentRepository;
import uk.co.boots.columbus.cmdb.model.node.domain.NodeSubEnvironment;
import uk.co.boots.columbus.cmdb.model.node.dto.NodeDTO;
import uk.co.boots.columbus.cmdb.model.node.repository.NodeSubEnvRepository;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;
import uk.co.boots.columbus.cmdb.model.server.repository.ServerRepository;

/**
 * A simple DTO Facility for Server.
 */
@Service
public class ServerDTOService {

	@Inject
	private ServerRepository serverRepository;
	@Inject
	private ServerTypeDTOService serverTypeDTOService;
	@Inject
	private SubEnvironmentDTOService subEnvironmentDTOService;
	@Inject
	private SubEnvironmentRepository seRepository;
	@Inject
	private NodeSubEnvRepository nseRepo;
	
	@Transactional(readOnly = true)
	public ServerDTO findOne(Long id) {
		return toDTO(serverRepository.findOne(id));
	}

	@Transactional(readOnly = true)
	public List<ServerDTO> complete(String query, int maxResults) {
		List<Server> results = serverRepository.complete(query, maxResults);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public PageResponse<ServerDTO> findAll(PageRequestByExample<ServerDTO> req) {
		Example<Server> example = null;
		Page<Server> page;
		if (req.example == null)
			page = serverRepository.findAll(req.toPageable());
		else
		{
			Server server = toEntity(req.example);
			example = Example.of(server);			
			if (server != null) {
				if (req.lazyLoadEvent != null && req.lazyLoadEvent.filters != null){
					// build the Matcher for this page request
					// probably a little overkill but should cope with all use cases
					for (Map.Entry<String, FilterMetadata> entry : req.lazyLoadEvent.filters.entrySet()){
						FilterMetadata filter = entry.getValue();
						// setup the matcher for contains / starts with or ends with
						ExampleMatcher matcher = ExampleMatcher.matching().withMatcher(entry.getKey(), match->filter.getMatcher(match));
						example = Example.of(server, matcher);		
					}
				}
			}
			page = serverRepository.findAll(example, req.toPageable());
		}

		List<ServerDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
		return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
	}

	@Transactional(readOnly = true)
	public PageResponse<ServerDTO> getServersNotInListForSubEnvironment(PageRequestByExample<SubEnvironmentDTO> req) {
		Page<Server> page = null;
		ArrayList<Long> ids = new ArrayList<Long>();
		for (NodeDTO s : req.example.servers){
				ids.add(s.id);
		}
			

		if (req.lazyLoadEvent != null && req.lazyLoadEvent.filters != null){
			
			FilterMetadata nameFilter = null;
			FilterMetadata typeFilter = null;

			if (req.lazyLoadEvent.filters.containsKey("name"))
				nameFilter = req.lazyLoadEvent.filters.get("name");
			if (req.lazyLoadEvent.filters.containsKey("serverType.name"))
				typeFilter = req.lazyLoadEvent.filters.get("serverType.name");
			
			if (nameFilter != null && typeFilter != null)
				page = serverRepository.findByNameContainsAndServerType_nameContainsAndIdNotIn(req.toPageable(), nameFilter.value, typeFilter.value, ids);
			else if (nameFilter != null)
				page = serverRepository.findByNameContainsAndIdNotIn(req.toPageable(), nameFilter.value, ids);
			else if (typeFilter != null)
				page = serverRepository.findByServerType_nameContainsAndIdNotIn(req.toPageable(), typeFilter.value, ids);
			else
				page = serverRepository.findByIdNotIn(req.toPageable(), ids);
		}
		else{
			page = serverRepository.findByIdNotIn(req.toPageable(), ids);
		}

		List<ServerDTO> content = page.getContent().stream().map((Server s) -> toDTO(s, 2)).collect(Collectors.toList()); 

		return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
			
	}

	
	/**
	 * Save the passed dto as a new entity or update the corresponding entity if
	 * any.
	 */
	@Transactional
	public ServerDTO save(ServerDTO dto) {

		Server server;
		
		if (dto.isIdSet()) {
			server = serverRepository.findOne(dto.id);
		} else {
			server = new Server();
		}

		server.setName(dto.name);
		server.setServerType(serverTypeDTOService.toEntity(dto.serverType));
		server = serverRepository.save(server);

		Set<NodeSubEnvironment> ses = server.getNodeSubEnvironments();
		if (dto.subEnvironments == null && dto.subEnvironments.size() == 0)
			server.setNodeSubEnvironments(null);

		else{
			List<NodeSubEnvironment> nseAddList = new ArrayList<NodeSubEnvironment>();
			for (SubEnvironmentDTO seDTO : dto.subEnvironments){
				Optional<NodeSubEnvironment> optional = ses.stream().filter(x -> x.getSubEnvironment().getId().equals(seDTO.id)).findFirst();
				if (!optional.isPresent()){
					// this is a new sub env for the server 
					NodeSubEnvironment nse = new NodeSubEnvironment();
					SubEnvironment se = seRepository.findOne(seDTO.id);
					nse.setNode(server);
					se.addNodeSubEnvironment(nse);
					nseAddList.add(nse);
				}
			}
			if (!nseAddList.isEmpty())
				nseRepo.save(nseAddList);
		}
		
		// Remove any old subEnvs
		// Only need to check this if updating and we already have subenvs
		if (dto.isIdSet() && ses != null && ses.size() > 0) {
			List<NodeSubEnvironment> nseRemoveList = new ArrayList<NodeSubEnvironment>();
			for (Iterator<NodeSubEnvironment> it = ses.iterator(); it.hasNext();) {
				NodeSubEnvironment nse = it.next();
				Optional<SubEnvironmentDTO> optional = dto.subEnvironments.stream().filter(x -> x.id.equals(nse.getSubEnvironment().getId())).findFirst();
				if (!optional.isPresent()) {
					// the DTO is not present so the relationship has been removed
					nseRemoveList.add(nse);
					it.remove();
				}
			}
			if (!nseRemoveList.isEmpty())
				nseRepo.delete(nseRemoveList);
		}

		dto.id = server.getId();
		return dto;
	}

	@Transactional(readOnly = true)
	public List<EnvironmentDTO> getEnvironmentsNotAssignedToServer(Long id) {
		Server s = serverRepository.findOne(id);
		// temp - needs changing to subenvironment
		return null;
		//return environmentDTOService.findEnvironmentsNotInList(s.getEnvironments());
	}

	@Transactional(readOnly = true)
	public List<ServerDTO> getServersNotInList(List<ServerDTO> serverList) {
		ArrayList<Long> ids = new ArrayList<Long>();
		for (ServerDTO s : serverList)
			ids.add(s.id);
		return toDTO(serverRepository.findByIdNotIn(ids), 2);
	}

	@Transactional(readOnly = true)
	public List<ServerDTO> getAll() {
		return toDTO(serverRepository.findAll(), 2);
	}
	
	/**
	 * Converts the passed server to a DTO.
	 */
	public ServerDTO toDTO(Server server) {
		return toDTO(server, 1);
	}

	/**
	 * Converts the passed server to a DTO. The depth is used to control the
	 * amount of association you want. It also prevents potential infinite
	 * serialization cycles.
	 *
	 * @param server
	 * @param depth
	 *            the depth of the serialization. A depth equals to 0, means no
	 *            x-to-one association will be serialized. A depth equals to 1
	 *            means that xToOne associations will be serialized. 2 means,
	 *            xToOne associations of xToOne associations will be serialized,
	 *            etc.
	 */
	public ServerDTO toDTO(Server server, int depth) {
		if (server == null) {
			return null;
		}

		ServerDTO dto = new ServerDTO();

		dto.id = server.getId();
		dto.serverId = server.getServerId();
		dto.name = server.getName();
		if (depth-- > 0) {
			dto.serverType = serverTypeDTOService.toDTO(server.getServerType(), depth);
			dto.subEnvironments = subEnvironmentDTOService.findSubEnvironmentsWithServer(dto);
		}

		return dto;
	}

	/**
	 * Converts the passed dto to a Server. Convenient for query by example.
	 */
	public Server toEntity(ServerDTO dto) {
		return toEntity(dto, 1);
	}

	/**
	 * Converts the passed dto to a Server. Convenient for query by example.
	 */
	public Server toEntity(ServerDTO dto, int depth) {
		if (dto == null) {
			return null;
		}
		Server server = new Server();
		server.setId(dto.id);
		server.setServerId(dto.serverId);
		server.setName(dto.name);
		if (depth-- > 0) {
			server.setServerType(serverTypeDTOService.toEntity(dto.serverType, depth));
			//server.setNodeSubEnvironments(subEnvironmentDTOService.toEntity(dto.subEnvironments, depth));
		}
		return server;
	}

	public Set<Server> toEntity(List<ServerDTO> dtoList, int depth) {
		if (dtoList == null)
			return null;
		Set<Server> ret = new HashSet<Server>();
		for (ServerDTO dto : dtoList)
			ret.add(toEntity(dto, depth));
		return ret;
	}

	public List<ServerDTO> toDTO(Set<Server> serverList, int depth) {
		if (serverList == null)
			return null;
		List<ServerDTO> servers = new ArrayList<ServerDTO>();
		for (Server s : serverList){
			servers.add(toDTO(s, depth));
		}
		return servers;
	}
	
	public List<ServerDTO> toDTO(List<Server> serverList, int depth) {
		if (serverList == null)
			return null;
		List<ServerDTO> servers = new ArrayList<ServerDTO>();
		for (Server s : serverList){
			servers.add(toDTO(s, depth));
		}
		return servers;
	}
}