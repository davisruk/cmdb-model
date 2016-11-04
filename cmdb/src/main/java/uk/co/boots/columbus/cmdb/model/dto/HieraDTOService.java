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
	
}
