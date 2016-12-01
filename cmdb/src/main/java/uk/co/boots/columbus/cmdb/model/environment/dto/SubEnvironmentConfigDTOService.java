package uk.co.boots.columbus.cmdb.model.environment.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.environment.domain.EnvironmentConfig;
import uk.co.boots.columbus.cmdb.model.environment.repository.SubEnvironmentRepository;
import uk.co.boots.columbus.cmdb.model.environment.rest.SubEnvironmentConfigRepository;

public class SubEnvironmentConfigDTOService {

    @Inject
    private SubEnvironmentConfigRepository subEnvironmentConfigRepository;
    @Inject
    private SubEnvironmentDTOService subEnvironmentDTOService;
    @Inject
    private SubEnvironmentRepository subEnvironmentRepository;

    @Transactional(readOnly = true)
    public SubEnvironmentConfigDTO findOne(Long id) {
        return toDTO(subEnvironmentConfigRepository.findOne(id));
    }

    private void buildHieraAddresses (List<EnvironmentConfig> cl){
    	String addr;
    	String value;
    	for (EnvironmentConfig conf: cl){
        	addr = conf.getHieraAddress();
        	value = conf.getValue();
        	//find Parameter in Hieara Address and replace with Parametername
        	if (addr != null){
        		addr = addr.replaceAll("\\{ParamName\\}",conf.getParameter());
        		addr = addr.replaceAll("\\{ENVID\\}", conf.getEnvironment().getName());
            	conf.setHieraAddress(addr);
        	}
        	if (value != null){
        		value = value.replaceAll("\\{ParamName\\}",conf.getParameter());
        		value = value.replaceAll("\\{ENVID\\}", conf.getEnvironment().getName());
        		conf.setValue(value);
        	}
        }
    }
    @Transactional(readOnly = true)
    public List<SubEnvironmentConfigDTO> findByEnvironmentName(String envName) {
        List<EnvironmentConfig> results = subEnvironmentConfigRepository.findBySubEnvironment_environment_name(envName);
        buildHieraAddresses (results);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EnvironmentConfigDTO> findByEnvironmentReleaseName(String relName) {
    	return null;
    }
}
