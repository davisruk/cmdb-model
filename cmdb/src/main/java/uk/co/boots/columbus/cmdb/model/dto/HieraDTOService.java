package uk.co.boots.columbus.cmdb.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import uk.co.boots.columbus.cmdb.model.repository.EnvironmentRepository;

@Service
public class HieraDTOService {

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


	// helper function to cope with the poor DTO structure
	// Each ConfigDTO implements CoreConfigDTO that exposes accessors
	private List<HieraDTO> addToHieraDTOList(List<HieraDTO> hieraList, List<? extends CoreConfigDTO> fromList){
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
		for (EnvironmentDTO e: eList)
			getHieraForRelease(hDTOList, e.release.name);
		return hDTOList;
	}

	private List<HieraDTO> getHieraForRelease(List<HieraDTO> hDTOList, String relName) {
		addToHieraDTOList(hDTOList, rcService.findByReleaseName(relName));
		addToHieraDTOList(hDTOList, ecService.findByEnvironmentReleaseName(relName));
		addToHieraDTOList(hDTOList, scService.findByServerEnvironmentReleaseName(relName));
		addToHieraDTOList(hDTOList, ccService.findByComponentPackageReleaseName(relName));
		return hDTOList;
	}
	
	public List<HieraDTO> findHieraCompleteInfoForRelease(String relName) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		getHieraForRelease(hDTOList, relName);
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
}
