package uk.co.boots.columbus.cmdb.model.hiera.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import uk.co.boots.columbus.cmdb.model.component.dto.ComponentConfigDTOService;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentConfigDTOService;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentConfigDTOService;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.globalconfig.dto.GlobalconfigDTOService;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseConfigDTOService;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerConfigDTOService;

@Service
public class HieraDTOService implements Comparator<HieraDTO> {

	@Inject
	private GlobalconfigDTOService gcService;
	@Inject
	private EnvironmentConfigDTOService ecService;
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

	public List<HieraDTO> findHieraInfoForEnvironment(String envName) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, ecService.findByEnvironmentName(envName));
		return hDTOList;
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
		addToHieraDTOList(hDTOList, ecService.findByEnvironmentReleaseName(relName));
		addToHieraDTOList(hDTOList, scService.findByServerSubEnvironmentReleaseName(relName));
		addToHieraDTOList(hDTOList, ccService.findByComponentPackageReleaseName(relName));
		return hDTOList;
	}

	public List<HieraDTO> findHieraCompleteInfoForRelease(String relName) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		getHieraForRelease(hDTOList, relName);
		return hDTOList;
	}

	public List<HieraDTO> findHieraCompleteInfoForSubEnv(Long subEnvId) {
		SubEnvironmentDTO subEnv = seDTOService.findOne(subEnvId,1);
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		addToHieraDTOList(hDTOList,
				secDTOService.findByTypeAndEnvironmentName(subEnv.subEnvironmentType.name, subEnv.environment.name));
		addToHieraDTOList(hDTOList,
				rcService.findByReleaseName(subEnv.release.name));
		addToHieraDTOList(hDTOList, scService.findByServerSubEnvironmentReleaseName(subEnv.release.name));
		addToHieraDTOList(hDTOList, ccService.findByComponentPackageReleaseName(subEnv.release.name));

		return hDTOList;
	}

	public List<HieraDTO> findHieraCompleteHieraInfo() {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
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
