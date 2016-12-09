package uk.co.boots.columbus.cmdb.model.environment.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.environment.domain.Environment;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironmentType;
import uk.co.boots.columbus.cmdb.model.environment.repository.EnvironmentRepository;
import uk.co.boots.columbus.cmdb.model.environment.repository.SubEnvironmentRepository;
import uk.co.boots.columbus.cmdb.model.environment.repository.SubEnvironmentTypeRepository;
import uk.co.boots.columbus.cmdb.model.node.domain.Node;
import uk.co.boots.columbus.cmdb.model.node.dto.NodeDTOService;
import uk.co.boots.columbus.cmdb.model.release.domain.Release;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseDTOService;
import uk.co.boots.columbus.cmdb.model.release.repository.ReleaseRepository;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTOService;
import uk.co.boots.columbus.cmdb.model.server.repository.ServerRepository;

@Service
public class SubEnvironmentDTOService {
	@Inject
	private SubEnvironmentRepository subEnvironmentRepository;
	@Inject
	private SubEnvironmentTypeRepository subEnvironmentTypeRepository;
	@Inject
	private ReleaseDTOService releaseDTOService;
	@Inject
	private ReleaseRepository releaseRepository;
	@Inject
	private ServerDTOService serverDTOService;
	@Inject
	private ServerRepository serverRepo;
	@Inject
	private EnvironmentRepository environmentRepository;
	@Inject
	private NodeDTOService nodeDTOService;
	@Inject
	private EnvironmentDTOService envDTOService;

	@Transactional(readOnly = true)
	public SubEnvironmentDTO findOne(Long id, int depth) {
		// return toDTO(subEnvironmentRepository.findOne(id));
		return toDTO(subEnvironmentRepository.findOne(id), depth);
	}

	@Transactional(readOnly = true)
	public List<SubEnvironmentDTO> complete(Long query, int maxResults) {
		List<SubEnvironment> results = subEnvironmentRepository.complete(query, maxResults);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	
	@Transactional(readOnly = true)
	public List<SubEnvironmentDTO> findSubEnvironmentsWithoutServer(ServerDTO serverDTO) {
		List<SubEnvironment> results = subEnvironmentRepository.findSubEnvsOfServer(serverDTO.id);
		List<Long> idList = new ArrayList<Long>();
		for (SubEnvironment se : results)
			idList.add(se.getId());
		if (idList.isEmpty())
			results = subEnvironmentRepository.findAll();
		else
			results = subEnvironmentRepository.findByIdNotIn(idList);
		//results = subEnvironmentRepository.findSubEnvsWithoutServer(serverDTO.id);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}
	@Transactional(readOnly = true)
	public List<SubEnvironmentDTO> findSubEnvironmentsWithServer(ServerDTO serverDTO) {
		List<SubEnvironment> results = subEnvironmentRepository.findSubEnvsOfServer(serverDTO.id);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<SubEnvironmentDTO> findAllSubEnvironments() {
		List<SubEnvironment> results = subEnvironmentRepository.findAll();
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public PageResponse<SubEnvironmentDTO> findAll(PageRequestByExample<SubEnvironmentDTO> req) {
		Example<SubEnvironment> example = null;
		SubEnvironment subEnvironment = toEntity(req.example);

		if (subEnvironment != null) {
			example = Example.of(subEnvironment);
		}

		Page<SubEnvironment> page;
		if (example != null) {
			page = subEnvironmentRepository.findAll(example, req.toPageable());
		} else {
			page = subEnvironmentRepository.findAll(req.toPageable());
		}

		List<SubEnvironmentDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
		return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
	}

	@Transactional(readOnly = true)
	public PageResponse<SubEnvironmentDTO> getSubEnvsNotInListForEnvironment(PageRequestByExample<EnvironmentDTO> req) {
		Page<SubEnvironment> page = null;
		ArrayList<Long> ids = new ArrayList<Long>();
		if (req == null || req.example == null)
			return null;

		for (SubEnvironmentDTO s : req.example.subEnvironments)
			ids.add(s.id);
		page = subEnvironmentRepository.findBySubEnvironmentType_IdNotIn(req.toPageable(), ids);

		List<SubEnvironmentDTO> content = page.getContent().stream().map((SubEnvironment s) -> toDTO(s, 0)).collect(Collectors.toList()); 

		return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
			
	}

	/**
	 * Save the passed dto as a new entity or update the corresponding entity if
	 * any.
	 */
	@Transactional
	public SubEnvironmentDTO save(SubEnvironmentDTO dto) {
		boolean inserting = false;

		if (dto == null) {
			return null;
		}

		SubEnvironment subEnvironment;
		if (dto.isIdSet()) {
			subEnvironment = subEnvironmentRepository.findOne(dto.id);
		} else {
			subEnvironment = new SubEnvironment();
			inserting = true;
		}

		if (dto.release == null) {
			subEnvironment.setRelease(null);
		} else {
			Release release = subEnvironment.getRelease();
			if (release == null || (release.getId().compareTo(dto.release.id) != 0)) {
				subEnvironment.setRelease(releaseRepository.findOne(dto.release.id));
			}
		}
		
		if (dto.subEnvironmentType == null) {
			subEnvironment.setSubEnvironmentType(null);
		} else {
			SubEnvironmentType set = subEnvironment.getSubEnvironmentType();
			if (set == null || (set.getId().compareTo(dto.subEnvironmentType.id) != 0)) {
				subEnvironment.setSubEnvironmentType(subEnvironmentTypeRepository.findOne(dto.subEnvironmentType.id));
			}
		}

		if (dto.environment == null) {
			subEnvironment.setEnvironment(null);
		} else {
			Environment env = subEnvironment.getEnvironment();
			if (env== null || (env.getId().compareTo(dto.environment.id) != 0)) {
				subEnvironment.setEnvironment(environmentRepository.findOne(dto.environment.id));
			}
		}

		Set<Node> nodes = subEnvironment.getNodes();
		
		// This is slow and clunky but if we are to remain stateless
		// there's no real alternative
		// Add any new servers
		if (dto.servers != null){
			for (ServerDTO sDTO : dto.servers) {
				Optional<Node> optional = nodes.stream().filter(x -> x.getId().equals(sDTO.id)).findFirst();
				if (!optional.isPresent()) {
					Server s = serverDTOService.toEntity(sDTO, 1);
					// add to both sides of the M:M
					// subenv owns the relationship
					subEnvironment.addNode(s,true);
				}
			}
		}
		// Remove any old nodes
		// Only need to check this if updating
		if (!inserting) {
			for (Iterator<Node> it = nodes.iterator(); it.hasNext();) {
				Node n = it.next();
				Optional<ServerDTO> optional = dto.servers.stream().filter(x -> x.id.equals(n.getId())).findFirst();
				if (!optional.isPresent()) {
						// same as above - ensure correct M:M persistence
						// by removing from both sides
						((Server)n).removeSubEnvironment(subEnvironment);
						it.remove();
				}
			}
		}
		subEnvironmentRepository.save(subEnvironment);
		return toDTO(subEnvironment, 2);
	}

	@Transactional
	public List<SubEnvironmentDTO> findSubEnvironmentsNotInList(List<SubEnvironment> envs) {
		List<SubEnvironment> results = subEnvironmentRepository.findAll();
		if (envs != null) {
			for (SubEnvironment env : envs) {
				results.remove(env);
			}
		}
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<SubEnvironmentTypeDTO> findAllSubEnvironmentTypes() {
		List<SubEnvironmentType> results = subEnvironmentTypeRepository.findAll();
		return results.stream().map(this::subEnvTypeToDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<SubEnvironmentTypeDTO> findAllSubEnvironmentTypesAvailableForEnvWithSubEnv(SubEnvironmentDTO seDTO) {
		List<SubEnvironmentType> results;
		List<Long> idList = new ArrayList<Long>();
		Environment e = environmentRepository.findOne(seDTO.environment.id);
		// if env has no sub environments then all sub env types are available
		if (e.getSubEnvironments() == null || e.getSubEnvironments().size() == 0)
			return findAllSubEnvironmentTypes();

		// otherwise we need to build a list of unavailable sub environment types 
		for (SubEnvironment se : e.getSubEnvironments()){
			idList.add(se.getSubEnvironmentType().getId());
		}
		// get the subenvironmenttypes not in the list
		results = subEnvironmentTypeRepository.findByIdNotIn(idList);
		// add in the subenvironment type that we're checking - could be null if new
		 if (seDTO.subEnvironmentType != null)
			 results.add(subEnvironmentTypeRepository.findOne(seDTO.subEnvironmentType.id));
		
		return results.stream().map(this::subEnvTypeToDTO).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<SubEnvironmentTypeDTO> findAllSubEnvironmentTypesAvailableForEnv(EnvironmentDTO eDTO) {
		List<SubEnvironmentType> results;
		List<Long> idList = new ArrayList<Long>();
		Environment e = environmentRepository.findOne(eDTO.id);
		// if env has no sub environments then all sub env types are available
		if (e.getSubEnvironments() == null || e.getSubEnvironments().size() == 0)
			return findAllSubEnvironmentTypes();

		// otherwise we need to build a list of unavailable sub environment types 
		for (SubEnvironment se : e.getSubEnvironments()){
			idList.add(se.getSubEnvironmentType().getId());
		}
		// get the subenvironmenttypes not in the list
		results = subEnvironmentTypeRepository.findByIdNotIn(idList);
	
		return results.stream().map(this::subEnvTypeToDTO).collect(Collectors.toList());
	}

	private SubEnvironmentTypeDTO subEnvTypeToDTO (SubEnvironmentType set){
		if (set == null)
			return null;
		SubEnvironmentTypeDTO setDTO = new SubEnvironmentTypeDTO();
		setDTO.id = set.getId();
		setDTO.name = set.getName();
		return setDTO;
	}	
	/**
	 * Converts the passed subEnvironment to a DTO.
	 */
	public SubEnvironmentDTO toDTO(SubEnvironment subEnvironment) {
		return toDTO(subEnvironment, 1);
	}

	/**
	 * Converts the passed subEnvironment to a DTO. The depth is used to control
	 * the amount of association you want. It also prevents potential infinite
	 * serialization cycles.
	 *
	 * @param subEnvironment
	 * @param depth
	 *            the depth of the serialization. A depth equals to 0, means no
	 *            x-to-one association will be serialized. A depth equals to 1
	 *            means that xToOne associations will be serialized. 2 means,
	 *            xToOne associations of xToOne associations will be serialized,
	 *            etc.
	 */
	public SubEnvironmentDTO toDTO(SubEnvironment subEnvironment, int depth) {
		if (subEnvironment == null) {
			return null;
		}

		SubEnvironmentDTO dto = new SubEnvironmentDTO();

		dto.id = subEnvironment.getId();
		dto.subEnvironmentType = new SubEnvironmentTypeDTO();
		SubEnvironmentType set = subEnvironment.getSubEnvironmentType();
		if (set == null){
			System.out.println("SET is NULL for SE:" + subEnvironment.getId());
		}
		dto.subEnvironmentType.id = subEnvironment.getSubEnvironmentType().getId();
		dto.subEnvironmentType.name = subEnvironment.getSubEnvironmentType().getName();
		if (depth-- > 0) {
			// Move to SubSubEnvironment
			dto.environment = envDTOService.toDTO(subEnvironment.getEnvironment());
			dto.release = releaseDTOService.toDTO(subEnvironment.getRelease(), depth);
			dto.servers = nodeDTOService.getServerDTOList(subEnvironment.getNodes(), depth);
		}

		return dto;
	}

	/**
	 * Converts the passed dto to a SubEnvironment. Convenient for query by
	 * example.
	 */
	public SubEnvironment toEntity(SubEnvironmentDTO dto) {
		return toEntity(dto, 1);
	}

	/**
	 * Converts the passed dto to a SubEnvironment. Convenient for query by
	 * example.
	 */
	public SubEnvironment toEntity(SubEnvironmentDTO dto, int depth) {
		if (dto == null) {
			return null;
		}

		SubEnvironment subEnvironment = new SubEnvironment();

		subEnvironment.setId(dto.id);
		if (depth-- > 0) {
			// Move to SubSubEnvironment
			//subEnvironment.setRelease(releaseDTOService.toEntity(dto.release, depth));
//			subEnvironment.setServers(serverDTOService.toEntity(dto.servers, depth));
		}

		return subEnvironment;
	}

	public Set<SubEnvironment> toEntity(List<SubEnvironmentDTO> dtoList, int depth) {
		if (dtoList == null)
			return null;
		Set<SubEnvironment> ret = new HashSet<SubEnvironment>();
		for (SubEnvironmentDTO dto : dtoList)
			ret.add(toEntity(dto, depth));
		return ret;
	}

	public List<SubEnvironmentDTO> toDTO(Set<SubEnvironment> envList, int depth) {
		if (envList == null)
			return null;
		List<SubEnvironmentDTO> ret = new ArrayList<SubEnvironmentDTO>();
		for (SubEnvironment e : envList)
			ret.add(toDTO(e, depth));
		return ret;
	}

	public List<SubEnvironmentDTO> toDTO(List<SubEnvironment> envList, int depth) {
		if (envList == null)
			return null;
		List<SubEnvironmentDTO> ret = new ArrayList<SubEnvironmentDTO>();
		for (SubEnvironment e : envList)
			ret.add(toDTO(e, depth));
		return ret;
	}
	
}
