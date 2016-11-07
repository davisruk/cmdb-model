package uk.co.boots.columbus.cmdb.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.tomcat.jni.Global;
import org.springframework.stereotype.Service;

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


	public List<HieraDTO> findHieraInfoForEnvironment(String envName) {
		List<EnvironmentConfigDTO> ecDTOList = ecService.findByEnvironmentName(envName);
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		for (EnvironmentConfigDTO ecDTO : ecDTOList) {
			hDTOList.add(new HieraDTO(ecDTO.value, ecDTO.hieraAddress));
		}
		return hDTOList;
	}

	public List<HieraDTO> findHieraCompleteInfoForRelease(String relName) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		
		List<GlobalconfigDTO> gcDTOList = gcService.findAllReplaceHiera();
		for (GlobalconfigDTO gcDTO : gcDTOList) {
			hDTOList.add(new HieraDTO(gcDTO.value, gcDTO.hieraAddress));
		}

		List<ReleaseConfigDTO> rcDTOList = rcService.findByReleaseName(relName);
		for (ReleaseConfigDTO rcDTO : rcDTOList) {
			hDTOList.add(new HieraDTO(rcDTO.value, rcDTO.hieraAddress));
		}
		
		List<EnvironmentConfigDTO> ecDTOList = ecService.findByEnvironmentReleaseName(relName);
		for (EnvironmentConfigDTO ecDTO : ecDTOList) {
			hDTOList.add(new HieraDTO(ecDTO.value, ecDTO.hieraAddress));
		}
	
		List<ServerConfigDTO> scDTOList = scService.findByServerEnvironmentReleaseName(relName);
		for (ServerConfigDTO scDTO : scDTOList) {
			hDTOList.add(new HieraDTO(scDTO.value, scDTO.hieraAddress));
		}

		List<ComponentConfigDTO> ccDTOList = ccService.findByComponentPackageReleaseName(relName);
		for (ComponentConfigDTO ccDTO : ccDTOList) {
			hDTOList.add(new HieraDTO(ccDTO.value, ccDTO.hieraAddress));
		}

		return hDTOList;
	}
	
	public List<HieraDTO> findHieraCompleteHieraInfo() {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		
		List<GlobalconfigDTO> gcDTOList = gcService.findAllReplaceHiera();
		for (GlobalconfigDTO gcDTO : gcDTOList) {
			hDTOList.add(new HieraDTO(gcDTO.value, gcDTO.hieraAddress));
		}

		List<ReleaseConfigDTO> rcDTOList = rcService.findByReleaseName(relName);
		for (ReleaseConfigDTO rcDTO : rcDTOList) {
			hDTOList.add(new HieraDTO(rcDTO.value, rcDTO.hieraAddress));
		}
		
		List<EnvironmentConfigDTO> ecDTOList = ecService.findByEnvironmentReleaseName(relName);
		for (EnvironmentConfigDTO ecDTO : ecDTOList) {
			hDTOList.add(new HieraDTO(ecDTO.value, ecDTO.hieraAddress));
		}
	
		List<ServerConfigDTO> scDTOList = scService.findByServerEnvironmentReleaseName(relName);
		for (ServerConfigDTO scDTO : scDTOList) {
			hDTOList.add(new HieraDTO(scDTO.value, scDTO.hieraAddress));
		}

		List<ComponentConfigDTO> ccDTOList = ccService.findByComponentPackageReleaseName(relName);
		for (ComponentConfigDTO ccDTO : ccDTOList) {
			hDTOList.add(new HieraDTO(ccDTO.value, ccDTO.hieraAddress));
		}

		return hDTOList;
	}


	public List<HieraDTO> findHieraInfoForServer(String envName) {
		List<ServerConfigDTO> scDTOList = scService.findByServerEnvironmentName(envName);
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		for (ServerConfigDTO scDTO : scDTOList) {
			hDTOList.add(new HieraDTO(scDTO.value, scDTO.hieraAddress));
		}
		return hDTOList;
	}

	public List<HieraDTO> findHieraInfoForRelease(String relName) {
		List<ReleaseConfigDTO> rcDTOList = rcService.findByReleaseName(relName);
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		for (ReleaseConfigDTO rcDTO : rcDTOList) {
			hDTOList.add(new HieraDTO(rcDTO.value, rcDTO.hieraAddress));
		}
		return hDTOList;
	}

	public List<HieraDTO> findHieraInfoForSolutionComponent(Long compId) {
		List<ComponentConfigDTO> ccDTOList = ccService.findBySolutionComponentId(compId);
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		for (ComponentConfigDTO ccDTO : ccDTOList) {
			hDTOList.add(new HieraDTO(ccDTO.value, ccDTO.hieraAddress));
		}
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
