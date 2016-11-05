package uk.co.boots.columbus.cmdb.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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


	public List<HieraDTO> findHieraInfoForEnvironment(String envName){
		List<GlobalconfigDTO> gcDTOList = gcService.findAllReplaceHiera();
		List<EnvironmentConfigDTO> ecDTOList = ecService.findByEnvironmentName(envName);
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		for (GlobalconfigDTO gcDTO: gcDTOList){
			hDTOList.add(new HieraDTO(gcDTO.value, gcDTO.hieraAddress));
		}
		for (EnvironmentConfigDTO ecDTO: ecDTOList){
			hDTOList.add(new HieraDTO(ecDTO.value, ecDTO.hieraAddress));
		}
		return hDTOList;
	}

	public List<HieraDTO> findHieraInfoForServer(String envName){
		List<GlobalconfigDTO> gcDTOList = gcService.findAllReplaceHiera();
		List<ServerConfigDTO> scDTOList = scService.findByServerEnvironmentName(envName);
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		for (GlobalconfigDTO gcDTO: gcDTOList){
			hDTOList.add(new HieraDTO(gcDTO.value, gcDTO.hieraAddress));
		}

		for (ServerConfigDTO scDTO: scDTOList){
			hDTOList.add(new HieraDTO(scDTO.value, scDTO.hieraAddress));
		}
		return hDTOList;
	}

	public List<HieraDTO> findHieraInfoForRelease(String relName){
		List<GlobalconfigDTO> gcDTOList = gcService.findAllReplaceHiera();
		List<ReleaseConfigDTO> rcDTOList = rcService.findByReleaseName(relName);
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		for (GlobalconfigDTO gcDTO: gcDTOList){
			hDTOList.add(new HieraDTO(gcDTO.value, gcDTO.hieraAddress));
		}
		for (ReleaseConfigDTO rcDTO: rcDTOList){
			hDTOList.add(new HieraDTO(rcDTO.value, rcDTO.hieraAddress));
		}
		return hDTOList;
	}

}
