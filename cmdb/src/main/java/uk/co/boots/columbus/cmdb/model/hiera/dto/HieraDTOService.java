package uk.co.boots.columbus.cmdb.model.hiera.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import uk.co.boots.columbus.cmdb.model.component.dto.ComponentConfigDTOService;
import uk.co.boots.columbus.cmdb.model.core.rest.support.CsvResponse;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentConfigDTOService;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.globalconfig.dto.GlobalconfigDTOService;
import uk.co.boots.columbus.cmdb.model.golbalconfig.domain.Globalconfig;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseConfigDTO;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseConfigDTOService;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerConfigDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerConfigDTOService;

@Service
public class HieraDTOService implements Comparator<HieraDTO> {

	@Inject
	private GlobalconfigDTOService gcService;
	@Inject
	private ServerConfigDTOService scService;
	@Inject
	private ReleaseConfigDTOService rcService;
	@Inject
	private ComponentConfigDTOService ccService;
	@Inject
	private EnvironmentDTOService eDTOService;
	@Inject
	private SubEnvironmentConfigDTOService secDTOService;
	@Inject
	private SubEnvironmentDTOService seDTOService;

	// helper function to cope with the poor DTO structure
	// Each ConfigDTO implements CoreConfigDTO that exposes accessors
	private List<HieraDTO> addToHieraDTOList(List<HieraDTO> hieraList, List<? extends CoreConfigDTO> fromList) {
		for (CoreConfigDTO cDTO : fromList)
			hieraList.add(new HieraDTO(cDTO.getValue(), cDTO.getHieraAddress()));

		return hieraList;
	}

	private Set<HieraDTO> addToHieraDTOSet(Set<HieraDTO> hieraSet, List<? extends CoreConfigDTO> fromList) {
		for (CoreConfigDTO cDTO : fromList)
			hieraSet.add(new HieraDTO(cDTO.getValue(), cDTO.getHieraAddress()));

		return hieraSet;
	}

	public List<HieraDTO> findHieraInfoForGlobalconfig() {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		return hDTOList;
	}

	public List<HieraDTO> findHieraCompleteInfoForSubEnv(Long subEnvId, boolean includeGlobal) {
		SubEnvironmentDTO subEnv = seDTOService.findOne(subEnvId, 1);
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		if (includeGlobal)
			addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		else
			addToHieraDTOList(hDTOList, gcService.findAndReplaceHieraForSubEnv());

		if (subEnv.subEnvironmentType != null)
			addToHieraDTOList(hDTOList, secDTOService.findByTypeAndEnvironmentName(subEnv.subEnvironmentType.name,
					subEnv.environment.name));
		if (subEnv.release != null) {
			addToHieraDTOList(hDTOList, ccService.findByComponentPackageReleaseName(subEnv.release.name));
		}
		
		return hDTOList;
	}

	public List<HieraDTO> findHieraCompleteInfoForSubEnv(Long subEnvId, Set<Globalconfig> gcSet) {
		SubEnvironmentDTO subEnv = seDTOService.findOne(subEnvId, 1);
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, gcService.findAndReplaceHieraForSubEnv(subEnv, gcSet));

		if (subEnv.subEnvironmentType != null)
			addToHieraDTOList(hDTOList, secDTOService.findByTypeAndEnvironmentName(subEnv.subEnvironmentType.name,
					subEnv.environment.name));
		if (subEnv.release != null) {
			addToHieraDTOList(hDTOList, ccService.findByComponentPackageReleaseName(subEnv.release.name));
		}
		
		return hDTOList;
	}

	public List<HieraDTO> findHieraCompleteInfoForEnv(Long EnvId, boolean includeGlobal) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		if (includeGlobal)
			addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		else{
			addToHieraDTOList(hDTOList, gcService.findAndReplaceHieraForEnv());
		}
			
		EnvironmentDTO dto = eDTOService.findOne(EnvId);
		List<ReleaseConfigDTO> relDTOs = rcService.getDistinctConfigsForEnv(dto.name);
		// Check recursive flag and add to list for releases
		addToHieraDTOList(hDTOList, relDTOs);
		for (SubEnvironmentDTO seDTO : dto.subEnvironments) {
			seDTO.environment = dto;
			hDTOList.addAll(findHieraCompleteInfoForSubEnv(seDTO.id, false));
			// add in recursive release config items here
			addToHieraDTOList(hDTOList, secDTOService.getSubEnvironmentDTOsWithHieraAddressesForRecursiveReleaseItems(seDTO, relDTOs));
		}
		List<ServerConfigDTO> serverConfDTOs = scService.findByDistinctServerSubEnvironmentEnvironment(dto.id);
		addToHieraDTOList(hDTOList, serverConfDTOs);
		return hDTOList;
	}
	
	public List<HieraDTO> findHieraCompleteInfoForEnv(Long EnvId, Set<Globalconfig> gcSet) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		EnvironmentDTO dto = eDTOService.findOne(EnvId);
		addToHieraDTOList(hDTOList, gcService.findAndReplaceHieraForEnv(dto, gcSet));
		
		List<ReleaseConfigDTO> relDTOs = rcService.getDistinctConfigsForEnv(dto.name);
		// Check recursive flag and add to list for releases
		addToHieraDTOList(hDTOList, relDTOs);
		for (SubEnvironmentDTO seDTO : dto.subEnvironments) {
			seDTO.environment = dto;
			hDTOList.addAll(findHieraCompleteInfoForSubEnv(seDTO.id, false));
			// add in recursive release config items here
			addToHieraDTOList(hDTOList, secDTOService.getSubEnvironmentDTOsWithHieraAddressesForRecursiveReleaseItems(seDTO, relDTOs));
			addToHieraDTOList(hDTOList, gcService.findAndReplaceHieraForRelease(seDTO, gcSet));
		}
		List<ServerConfigDTO> serverConfDTOs = scService.findByDistinctServerSubEnvironmentEnvironment(dto.id);
		addToHieraDTOList(hDTOList, serverConfDTOs);
		return hDTOList;
	}

	public List<HieraDTO> findHieraCompleteInfoForAllEnvs() {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		Set<Globalconfig> gcSet = new HashSet<Globalconfig>();
		//addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera(gcSet));
		List<EnvironmentDTO> dtoList = eDTOService.findAllEnvironments();
		for (EnvironmentDTO dto:dtoList){
//			hDTOList.addAll(findHieraCompleteInfoForEnv(dto.id, false));
			hDTOList.addAll(findHieraCompleteInfoForEnv(dto.id, gcSet));
		}
		return hDTOList;
	}
	
	public Set<HieraDTO> getHieraForAllEnvsWithSubstitution(){
		Set<HieraDTO> hDTOSet = new HashSet<HieraDTO>();
		addToHieraDTOSet(hDTOSet, gcService.findAllReplaceHiera());
		List<EnvironmentDTO> dtoList = eDTOService.findAllEnvironments();
		for (EnvironmentDTO dto:dtoList){
			hDTOSet.addAll(findHieraCompleteInfoForEnv(dto.id, false));
		}		
		return hDTOSet;
	}
	
	public Set<HieraDTO> getHieraCompleteInfoForEnvWithSubstitution(Long EnvId, Set<HieraDTO> hDTOSet) {
		// we may have already added this, but the set will take care of it anyway and we may have repeating elements
		addToHieraDTOSet(hDTOSet, gcService.findAllReplaceHiera());
		EnvironmentDTO dto = eDTOService.findOne(EnvId);
		List<ReleaseConfigDTO> relDTOs = rcService.getDistinctConfigsForEnv(dto.name);
		// Check recursive flag and add to list for releases
		addToHieraDTOSet(hDTOSet, relDTOs);
		for (SubEnvironmentDTO seDTO : dto.subEnvironments) {
			seDTO.environment = dto;
			hDTOSet.addAll(findHieraCompleteInfoForSubEnv(seDTO.id, false));
			// add in recursive release config items here
			addToHieraDTOSet(hDTOSet, secDTOService.getSubEnvironmentDTOsWithHieraAddressesForRecursiveReleaseItems(seDTO, relDTOs));
		}
		List<ServerConfigDTO> serverConfDTOs = scService.findByDistinctServerSubEnvironmentEnvironment(dto.id);
		addToHieraDTOSet(hDTOSet, serverConfDTOs);
		return hDTOSet;
	}
	
	public List<HieraDTO> findHieraInfoForServer(String serverName) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, scService.findByServerName(serverName));
		return hDTOList;
	}

	public List<HieraDTO> findHieraInfoForRelease(String relName) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, rcService.findByReleaseName(relName));
		return hDTOList;
	}

	public List<HieraDTO> findHieraInfoForSolutionComponent(Long compId) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, ccService.findBySolutionComponentId(compId));
		return hDTOList;
	}

	public List<String[]> convertForCSV(CsvResponse csv, boolean includeHeaders) {
		Collection<HieraDTO> coll = csv.getRecords();
		if (coll == null)
			coll = csv.getUniqueRecords();

		List<String[]> result = new ArrayList<String[]>();
		if (includeHeaders)
			result.add(new String[] { "Address", "Value" });
		for (HieraDTO h : coll) {
			result.add(new String[] { h.address, h.value });
		}
		return result;
	}

	public List<String[]> convertForCSV(List<HieraDTO> list, boolean includeHeaders) {
		List<String[]> result = new ArrayList<String[]>();
		if (includeHeaders)
			result.add(new String[] { "Address", "Value" });
		for (HieraDTO h : list) {
			result.add(new String[] { h.address, h.value });
		}
		return result;
	}

	public List<String[]> convertForCSV(Set<HieraDTO> set, boolean includeHeaders) {
		List<String[]> result = new ArrayList<String[]>();
		if (includeHeaders)
			result.add(new String[] { "Address", "Value" });
		for (HieraDTO h : set) {
			result.add(new String[] { h.address, h.value });
		}
		return result;
	}

	@Override
	public int compare(HieraDTO o1, HieraDTO o2) {
		return o1.address.compareTo(o2.address);
	}
}
