package uk.co.boots.columbus.cmdb.model.component.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.component.domain.ComponentConfig;
import uk.co.boots.columbus.cmdb.model.component.domain.SolutionComponent;
import uk.co.boots.columbus.cmdb.model.component.repository.ComponentConfigRepository;
import uk.co.boots.columbus.cmdb.model.component.repository.SolutionComponentRepository;
import uk.co.boots.columbus.cmdb.model.core.domain.LockStrategy;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.security.util.SecurityHelper;

/**
 * A simple DTO Facility for ComponentConfig.
 */
@Service
public class ComponentConfigDTOService {

    @Inject
    private ComponentConfigRepository componentConfigRepository;
    @Inject
    private SolutionComponentDTOService solutionComponentDTOService;
    @Inject
    private SolutionComponentRepository solutionComponentRepository;
    @Inject
    private LockStrategy lockStrategy;
    
    @Transactional(readOnly = true)
    public ComponentConfigDTO findOne(Long id) {
        return toDTO(componentConfigRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<ComponentConfigDTO> complete(String query, int maxResults) {
        List<ComponentConfig> results = componentConfigRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ComponentConfigDTO> findBySolutionComponentId(Long id) {
        List<ComponentConfig> results = componentConfigRepository.findBySolutionComponent_id(id);
        buildHieraAddresses (results);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ComponentConfigDTO> findByComponentPackageReleaseName(String relName) {
        List<ComponentConfig> results = componentConfigRepository.findBySolutionComponent_PackageInfo_Release_name(relName);
        buildHieraAddresses (results);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    
    private void buildHieraAddresses (List<ComponentConfig> cl){
    	String addr;
    	String value;
    	boolean allowSensitive = SecurityHelper.userCanViewSensitiveData();
    	for (ComponentConfig conf: cl){
        	addr = conf.getHieraAddress();
        	//find Parameter in Hieara Address and replace with Release
        	addr = addr.replaceAll("\\{Release\\}",conf.getSolutionComponent().getPackageInfo().getRelease().getName());
        	//find EnvTag in Hieara Address and replace with ServType
        	addr = addr.replaceAll("\\{ServType\\}", conf.getSolutionComponent().getPackageInfo().getServerType().getName());
        	//find EnvTag in Hieara Address and replace with Env.name
        	addr = addr.replaceAll("\\{AppName\\}", conf.getSolutionComponent().getName());

        	if (conf.IsSensitive() && !allowSensitive)
				value = "[SENSITIVE]";
        	else{
		    	value = conf.getValue();
		    	if (value != null){
			    	//find Parameter in Hieara Address and replace with Release
			    	value = value.replaceAll("\\{Release\\}",conf.getSolutionComponent().getPackageInfo().getRelease().getName());
			    	//find EnvTag in Hieara Address and replace with ServType
			    	value = value.replaceAll("\\{ServType\\}", conf.getSolutionComponent().getPackageInfo().getServerType().getName());
			    	//find EnvTag in Hieara Address and replace with Env.name
			    	value = value.replaceAll("\\{AppName\\}", conf.getSolutionComponent().getName());
		    	}
        	}
        	conf.setHieraAddress(addr);
        	conf.setValue(value);
        }
    }

    
    @Transactional(readOnly = true)
    public PageResponse<ComponentConfigDTO> findAll(PageRequestByExample<ComponentConfigDTO> req) {
        Example<ComponentConfig> example = null;
        ComponentConfig componentConfig = toEntity(req.example);

        if (componentConfig != null) {
            example = Example.of(componentConfig);
        }

        Page<ComponentConfig> page;
        if (example != null) {
            page = componentConfigRepository.findAll(example, req.toPageable());
        } else {
            page = componentConfigRepository.findAll(req.toPageable());
        }

        List<ComponentConfigDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public ComponentConfigDTO save(ComponentConfigDTO dto){
        if (dto == null) {
            return null;
        }

        ComponentConfig componentConfig;
		if (dto.isIdSet()) {
			componentConfig = componentConfigRepository.findOne(dto.id);
			if (!componentConfig.IsSensitive()
					|| (componentConfig.IsSensitive() && SecurityHelper.userCanWriteSensitiveData())) {
				// only ever set sensitive data if we are allowed to
				// value is set to [SENSITIVE] when retrieved from
				// the DB for the DTO - we don't want to overwrite
				// the real value.
				componentConfig.setValue(dto.value);
				componentConfig.setSensitive(dto.sensitive);
			}
			lockStrategy.lockEntity(componentConfig, dto);
//			if (dto.version != componentConfig.getVersion()){ 
//				throw new OptimisticLockException(toDTO(componentConfig));
//			}
		} else {
			componentConfig = new ComponentConfig();
			// sensitive data can always be set here as it's a new item
			// if the user has rights they will have been checked in the
			// UI.
			componentConfig.setValue(dto.value);
			componentConfig.setSensitive(dto.sensitive);
		}

        componentConfig.setParameter(dto.parameter);
        componentConfig.setHieraAddress(dto.hieraAddress);
        componentConfig.setNotes(dto.notes);
        
        if (dto.my_component == null) {
            componentConfig.setSolutionComponent(null);
        } else {
            SolutionComponent my_component = componentConfig.getSolutionComponent();
            if (my_component == null || (my_component.getId().compareTo(dto.my_component.id) != 0)) {
                componentConfig.setSolutionComponent(solutionComponentRepository.findOne(dto.my_component.id));
            }
        }

        return toDTO(componentConfigRepository.save(componentConfig));
    }

    /**
     * Converts the passed componentConfig to a DTO.
     */
    public ComponentConfigDTO toDTO(ComponentConfig componentConfig) {
        return toDTO(componentConfig, 1);
    }

    /**
     * Converts the passed componentConfig to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param componentConfig
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public ComponentConfigDTO toDTO(ComponentConfig componentConfig, int depth) {
        if (componentConfig == null) {
            return null;
        }

        ComponentConfigDTO dto = new ComponentConfigDTO();

        dto.id = componentConfig.getId();
        dto.parameter = componentConfig.getParameter();
		dto.value = componentConfig.getValue();
		// hide sensitive info if user doesn't have rights
		if (componentConfig.IsSensitive() && !SecurityHelper.userCanViewSensitiveData())
			dto.value = "[SENSITIVE]";
		else
			dto.value = componentConfig.getValue();

        dto.hieraAddress = componentConfig.getHieraAddress();
		dto.notes = componentConfig.getNotes();
		dto.sensitive = componentConfig.IsSensitive();
        dto.version = componentConfig.getVersion();
        if (depth-- > 0) {
            dto.my_component = solutionComponentDTOService.toDTO(componentConfig.getSolutionComponent(), depth);
        }

        return dto;
    }

    /**
     * Converts the passed dto to a ComponentConfig.
     * Convenient for query by example.
     */
    public ComponentConfig toEntity(ComponentConfigDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a ComponentConfig.
     * Convenient for query by example.
     */
    public ComponentConfig toEntity(ComponentConfigDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        ComponentConfig componentConfig = new ComponentConfig();

        componentConfig.setId(dto.id);
        componentConfig.setParameter(dto.parameter);
        componentConfig.setValue(dto.value);
        componentConfig.setHieraAddress(dto.hieraAddress);
        componentConfig.setSensitive(dto.sensitive);
        componentConfig.setNotes(dto.notes);
        componentConfig.setVersion(dto.version);
        
        if (depth-- > 0) {
            componentConfig.setSolutionComponent(solutionComponentDTOService.toEntity(dto.my_component, depth));
        }

        return componentConfig;
    }
}