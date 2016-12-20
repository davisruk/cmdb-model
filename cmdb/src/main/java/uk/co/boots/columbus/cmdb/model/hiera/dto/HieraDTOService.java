package uk.co.boots.columbus.cmdb.model.hiera.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import uk.co.boots.columbus.cmdb.model.component.dto.ComponentConfigDTOService;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentConfigDTOService;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.globalconfig.dto.GlobalconfigDTOService;
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

	public List<HieraDTO> findHieraInfoForGlobalconfig() {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		return hDTOList;
	}

	public List<HieraDTO> findAllHiera() {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		List<EnvironmentDTO> eList = eDTOService.findAllEnvironments();
		/*
		 * Move to SubEnvironment for (EnvironmentDTO e: eList){ if (e.release
		 * != null) getHieraForRelease(hDTOList, e.release.name); }
		 */
		hDTOList.sort(this);
		return hDTOList;
	}

	private List<HieraDTO> getHieraForRelease(List<HieraDTO> hDTOList, String relName) {
		addToHieraDTOList(hDTOList, rcService.findByReleaseName(relName));
		// addToHieraDTOList(hDTOList,
		// secDTOService.findBySubEnvironmentReleaseName(relName, true));
		//addToHieraDTOList(hDTOList, scService.findByServerSubEnvironmentReleaseName(relName));
		addToHieraDTOList(hDTOList, ccService.findByComponentPackageReleaseName(relName));
		return hDTOList;
	}

	public List<HieraDTO> findHieraCompleteInfoForSubEnv(Long subEnvId, boolean includeGlobal) {
		SubEnvironmentDTO subEnv = seDTOService.findOne(subEnvId, 1);
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		if (includeGlobal)
			addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());

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
		EnvironmentDTO dto = eDTOService.findOne(EnvId);
		List<ReleaseConfigDTO> relDTOs = rcService.getDistinctConfigsForEnv(dto.name);
		// Check recursive flag and add to list for releases
		addToHieraDTOList(hDTOList, relDTOs);
		for (SubEnvironmentDTO seDTO : dto.subEnvironments) {
			hDTOList.addAll(findHieraCompleteInfoForSubEnv(seDTO.id, false));
			// add in recursive release config items here
			addToHieraDTOList(hDTOList, secDTOService.getSubEnvironmentDTOsWithHieraAddressesForRecursiveReleaseItems(seDTO, relDTOs));
		}
		List<ServerConfigDTO> serverConfDTOs = scService.findByDistinctServerSubEnvironmentEnvironment(dto.id);
		addToHieraDTOList(hDTOList, serverConfDTOs);
		return hDTOList;
	}
	
	public List<HieraDTO> findHieraCompleteInfoForAllEnvs() {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		List<EnvironmentDTO> dtoList = eDTOService.findAllEnvironments();
		for (EnvironmentDTO dto:dtoList){
			hDTOList.addAll(findHieraCompleteInfoForEnv(dto.id, false));
		}
		return hDTOList;
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

	public List<String[]> convertForCSV(List<HieraDTO> list, boolean includeHeaders) {
		List<String[]> result = new ArrayList<String[]>();
		if (includeHeaders)
			result.add(new String[] { "Address", "Value" });
		for (HieraDTO h : list) {
			result.add(new String[] { h.address, h.value });
		}
		return result;
	}

	@Override
	public int compare(HieraDTO o1, HieraDTO o2) {
		return o1.address.compareTo(o2.address);
	}
}
