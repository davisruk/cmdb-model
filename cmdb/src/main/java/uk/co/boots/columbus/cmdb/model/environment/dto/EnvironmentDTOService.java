package uk.co.boots.columbus.cmdb.model.environment.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import uk.co.boots.columbus.cmdb.model.environment.domain.Environment;
import uk.co.boots.columbus.cmdb.model.environment.domain.EnvironmentType;
import uk.co.boots.columbus.cmdb.model.environment.repository.EnvironmentRepository;
import uk.co.boots.columbus.cmdb.model.environment.repository.EnvironmentTypeRepository;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseDTOService;
import uk.co.boots.columbus.cmdb.model.release.repository.ReleaseRepository;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTOService;
import uk.co.boots.columbus.cmdb.model.server.repository.ServerRepository;

/**
 * A simple DTO Facility for Environment.
 */
@Service
public class EnvironmentDTOService {

	@Inject
	private EnvironmentRepository environmentRepository;
	@Inject
	private EnvironmentTypeRepository environmentTypeRepository;
	@Inject
	ServerDTOService serverDTOService;
	private @Inject
	ServerRepository serverRepo;
	@Inject
	SubEnvironmentDTOService subEnvironmentDTOService;


	@Transactional(readOnly = true)
	public EnvironmentDTO findOne(Long id) {
		// return toDTO(environmentRepository.findOne(id));
		return toDTO(environmentRepository.findOne(id));
	}

	@Transactional(readOnly = true)
	public List<EnvironmentDTO> complete(String query, int maxResults) {
		List<Environment> results = environmentRepository.complete(query, maxResults);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<EnvironmentDTO> findAllEnvironments() {
		List<Environment> results = environmentRepository.findAll();
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<EnvironmentTypeDTO> findAllEnvironmentTypes() {
		List<EnvironmentType> results = environmentTypeRepository.findAll();
		return results.stream().map((EnvironmentType et) -> envTypeToDTO(et)).collect(Collectors.toList());
	}
	
	private EnvironmentTypeDTO envTypeToDTO (EnvironmentType et){
		if (et == null)
			return null;
		EnvironmentTypeDTO eDTO = new EnvironmentTypeDTO();
		eDTO.id = et.getId();
		eDTO.name = et.getName();
		return eDTO;
	}	
	
	private EnvironmentType envTypeDTOToEntity (EnvironmentTypeDTO etDTO){
		if (etDTO == null)
			return null;
		EnvironmentType et = new EnvironmentType();
		et.setId(etDTO.id);
		et.setName(etDTO.name);
		return et;
	}	

	@Transactional(readOnly = true)
	public PageResponse<EnvironmentDTO> findAll(PageRequestByExample<EnvironmentDTO> req) {
		Example<Environment> example = null;
		Page<Environment> page;
		if (req.example == null)
			page = environmentRepository.findAll(req.toPageable());
		else
		{
			Environment env = toEntity(req.example);
			example = Example.of(env);			
			if (env != null) {
				if (req.lazyLoadEvent != null && req.lazyLoadEvent.filters != null && req.lazyLoadEvent.filters.size() > 0){
					// build the Matcher for this page request
					// probably a little overkill but should cope with all use cases
					for (Map.Entry<String, FilterMetadata> entry : req.lazyLoadEvent.filters.entrySet()){
						FilterMetadata filter = entry.getValue();
						// setup the matcher for contains / starts with or ends with
						ExampleMatcher matcher = ExampleMatcher.matching().withMatcher(entry.getKey(), match->filter.getMatcher(match));
						example = Example.of(env, matcher);		
					}
				}
			}
			page = environmentRepository.findAll(example, req.toPageable());
		}

		List<EnvironmentDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
		return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
	}

	
	/**
	 * Save the passed dto as a new entity or update the corresponding entity if
	 * any.
	 */
	@Transactional
	public EnvironmentDTO save(EnvironmentDTO dto) {
		boolean inserting = false;

		if (dto == null) {
			return null;
		}

		Environment environment;
		if (dto.isIdSet()) {
			environment = environmentRepository.findOne(dto.id);
		} else {
			environment = new Environment();
		}

		environment.setName(dto.name);
		environment.setEnvironmentType(envTypeDTOToEntity(dto.type));
/* Move to SubEnvironment
		if (dto.release == null) {
			environment.setRelease(null);
		} else {
			Release release = environment.getRelease();
			if (release == null || (release.getId().compareTo(dto.release.id) != 0)) {
				environment.setRelease(releaseRepository.findOne(dto.release.id));
			}
		}
*/		
		environmentRepository.save(environment);
		
		List<Server> servers = environment.getServers();
		if (servers == null) {
			servers = new ArrayList<>();
			environment.setServers(servers);
			inserting=true;
		}

		// This is slow and clunky but if we are to remain stateless
		// there's no real alternative
		// Add any new servers
		if (dto.servers != null){
			for (ServerDTO sDTO : dto.servers) {
				Optional<Server> optional = servers.stream().filter(x -> x.getId().equals(sDTO.id)).findFirst();
				if (!optional.isPresent()) {
					// Add the server to the environment
					// We need to do this because Server owns the M:M relationship
					// Environment will not persist changes to the join table
					Server s = serverDTOService.toEntity(sDTO, 1);
					//s.addEnvironment(environment);
					serverRepo.save(s);
					environment.getServers().add(s);
				}
			}
		}
		// Remove any old servers
		// Only need to check this if updating
		if (!inserting) {
			for (Iterator<Server> it = servers.iterator(); it.hasNext();) {
				Server s = it.next();
				Optional<ServerDTO> optional = dto.servers.stream().filter(x -> x.id.equals(s.getId())).findFirst();
				if (!optional.isPresent()) {
					// same as above - we need to ensure we persist the M:M relationships
					//s.removeEnvironment(environment);
					serverRepo.save(s);
					it.remove();
				}
			}
		}
		return toDTO(environment, 2);
	}

	@Transactional
	public List<EnvironmentDTO> findEnvironmentsNotInList(List<Environment> envs) {
		List<Environment> results = environmentRepository.findAll();
		if (envs != null) {
			for (Environment env : envs) {
				results.remove(env);
			}
		}
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	/**
	 * Converts the passed environment to a DTO.
	 */
	public EnvironmentDTO toDTO(Environment environment) {
		return toDTO(environment, 1);
	}

	/**
	 * Converts the passed environment to a DTO. The depth is used to control
	 * the amount of association you want. It also prevents potential infinite
	 * serialization cycles.
	 *
	 * @param environment
	 * @param depth
	 *            the depth of the serialization. A depth equals to 0, means no
	 *            x-to-one association will be serialized. A depth equals to 1
	 *            means that xToOne associations will be serialized. 2 means,
	 *            xToOne associations of xToOne associations will be serialized,
	 *            etc.
	 */
	public EnvironmentDTO toDTO(Environment environment, int depth) {
		if (environment == null) {
			return null;
		}

		EnvironmentDTO dto = new EnvironmentDTO();

		dto.id = environment.getId();
		dto.name = environment.getName();
		dto.type = envTypeToDTO(environment.getEnvironmentType());
		if (depth-- > 0) {
			// Move to SubEnvironment
			//dto.release = releaseDTOService.toDTO(environment.getRelease(), depth);
			//dto.servers = serverDTOService.toDTO(environment.getServers(), depth);
			dto.subEnvironments = subEnvironmentDTOService.toDTO(environment.getSubEnvironments(), depth);
		}

		return dto;
	}

	/**
	 * Converts the passed dto to a Environment. Convenient for query by
	 * example.
	 */
	public Environment toEntity(EnvironmentDTO dto) {
		return toEntity(dto, 1);
	}

	/**
	 * Converts the passed dto to a Environment. Convenient for query by
	 * example.
	 */
	public Environment toEntity(EnvironmentDTO dto, int depth) {
		if (dto == null) {
			return null;
		}

		Environment environment = new Environment();

		environment.setId(dto.id);
		environment.setName(dto.name);
		if (depth-- > 0) {
			// Move to SubEnvironment
			//environment.setRelease(releaseDTOService.toEntity(dto.release, depth));
			//environment.setServers(serverDTOService.toEntity(dto.servers, depth));
		}

		return environment;
	}

	public List<Environment> toEntity(List<EnvironmentDTO> dtoList, int depth) {
		if (dtoList == null)
			return null;
		List<Environment> ret = new ArrayList<Environment>();
		for (EnvironmentDTO dto : dtoList)
			ret.add(toEntity(dto, depth));
		return ret;
	}

	public List<EnvironmentDTO> toDTO(List<Environment> envList, int depth) {
		if (envList == null)
			return null;
		List<EnvironmentDTO> ret = new ArrayList<EnvironmentDTO>();
		for (Environment e : envList)
			ret.add(toDTO(e, depth));
		return ret;
	}

}