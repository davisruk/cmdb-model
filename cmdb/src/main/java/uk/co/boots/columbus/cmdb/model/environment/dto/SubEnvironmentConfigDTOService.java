package uk.co.boots.columbus.cmdb.model.environment.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironmentConfig;
import uk.co.boots.columbus.cmdb.model.environment.repository.SubEnvironmentConfigRepository;
import uk.co.boots.columbus.cmdb.model.environment.repository.SubEnvironmentRepository;
import uk.co.boots.columbus.cmdb.model.globalconfig.dto.GlobalconfigDTO;
import uk.co.boots.columbus.cmdb.model.hiera.dto.ConfigTokenHelper;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseConfigDTO;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseDTO;
import uk.co.boots.columbus.cmdb.model.security.util.SecurityHelper;

@Service
public class SubEnvironmentConfigDTOService {

	@Inject
	private SubEnvironmentConfigRepository subEnvironmentConfigRepository;
	@Inject
	private SubEnvironmentRepository subEnvironmentRepository;
	@Inject
	private SubEnvironmentDTOService subEnvironmentDTOService;
	
	@Transactional(readOnly = true)
	public SubEnvironmentConfigDTO findOne(Long id, int depth) {
		return toDTO(subEnvironmentConfigRepository.findOne(id), depth);
	}

	private void buildHieraAddresses(List<SubEnvironmentConfig> cl) {
		String addr;
		String value;
		boolean allowSensitive = SecurityHelper.userCanViewSensitiveData();
		for (SubEnvironmentConfig conf : cl) {
			addr = conf.getHieraAddress();
			value = conf.getValue();
			// find Parameter in Hieara Address and replace with Parametername
			if (addr != null) {
				addr = addr.replaceAll("\\{ParamName\\}", conf.getParameter());
				addr = addr.replaceAll("\\{ENVID\\}", conf.getSubEnvironment().getEnvironment().getName());
			}
			if (value != null) {
				value = value.replaceAll("\\{ParamName\\}", conf.getParameter());
				value = value.replaceAll("\\{ENVID\\}", conf.getSubEnvironment().getEnvironment().getName());
				if (conf.IsSensitive() && !allowSensitive)
					value = "[SENSITIVE]";
			}
			conf.setHieraAddress(addr);
			conf.setValue(value);
		}
	}

	public List<SubEnvironmentConfigDTO> populateHieraAddresses(List<SubEnvironmentConfigDTO> secl) {
		List<SubEnvironmentConfigDTO> populatedConfig = new ArrayList<SubEnvironmentConfigDTO>();
		boolean allowSensitive = SecurityHelper.userCanViewSensitiveData();
		for (Iterator<SubEnvironmentConfigDTO> it = secl.iterator(); it.hasNext();){
			SubEnvironmentConfigDTO conf = it.next();
			SubEnvironmentConfigDTO populatedConf = conf.getCopy();
			// find Parameter in Hieara Address and replace with Parametername

			ConfigTokenHelper.replaceTags(populatedConf, "ParamName", conf.getParameter());
			ConfigTokenHelper.replaceTags(populatedConf, "ENVID", conf.subEnvironment.environment.name);
			ConfigTokenHelper.replaceTags(populatedConf, "SubEnvType", conf.subEnvironment.subEnvironmentType.name);
			ConfigTokenHelper.replaceTags(populatedConf, "Release", conf.subEnvironment.subEnvironmentType.name);

			if (populatedConf.value != null) {
				if (!allowSensitive && conf.sensitive) {
					populatedConf.value = "[SENSITIVE]";
				}
			}
			populatedConfig.add(populatedConf);
		}
		return populatedConfig;
	}	

	// Awful, awful method that understands the context in which it is being called i.e. the list
	// it returns will be added to a Set to guarantee uniqueness of the generated hiera strings.
	// It is possible for ConfigItems at this level to override any recursive items such as ReleaseConfig
	// Due to the fact substitution is done here the resulting Set (outside this method!) will allow the
	// original config AND the overridden config into the collection because they are both unique
	// This entire mechanism needs overhauling - probably add the substituted HieraAddress to the config items
	// and store these in the Set.
	public List<SubEnvironmentConfigDTO> getSubEnvironmentDTOsWithHieraAddressesForRecursiveReleaseItems(
			SubEnvironmentDTO seDTO, List<ReleaseConfigDTO> relConfList) {
		boolean allowSensitive = SecurityHelper.userCanViewSensitiveData();
		List<SubEnvironmentConfigDTO> subConList = new ArrayList<SubEnvironmentConfigDTO>();
		for (ReleaseConfigDTO rDTO : relConfList) {
			if (rDTO.recursiveBySubEnv.booleanValue()) {
				SubEnvironmentConfigDTO dto = new SubEnvironmentConfigDTO();
				dto.hieraAddress = rDTO.hieraAddress.replaceAll("\\{ParamName\\}", rDTO.getParameter());
				dto.hieraAddress = dto.hieraAddress.replaceAll("\\{ENVID\\}", seDTO.environment.name);
				if (allowSensitive){
					dto.value = rDTO.value.replaceAll("\\{ParamName\\}", rDTO.getParameter());
					dto.value = dto.value.replaceAll("\\{ENVID\\}", seDTO.environment.name);
				}else{
					dto.value = "[SENSITIVE]";
				}
				// only add to the list if not already overridden
				// grossly inefficient and slow but ultimately we are using 
				// athe final SET will allow the 
				List<SubEnvironmentConfig> secList = subEnvironmentConfigRepository.findByHieraAddressAndId(rDTO.hieraAddress, seDTO.id);
				if (secList == null || secList.size() == 0)
					subConList.add(dto);
			}
		}
		return subConList;
	}

	

	@Transactional(readOnly = true)
	public List<SubEnvironmentConfigDTO> findByTypeAndEnvironmentName(String typeName, String envName) {
		List<SubEnvironmentConfig> results = subEnvironmentConfigRepository
				.findBySubEnvironment_subEnvironmentType_nameAndSubEnvironment_environment_name(typeName, envName);
		buildHieraAddresses(results);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<SubEnvironmentConfigDTO> getSubEnvConfigByTypeAndEnvironmentName(String typeName, String envName) {
		List<SubEnvironmentConfig> results = subEnvironmentConfigRepository
				.findBySubEnvironment_subEnvironmentType_nameAndSubEnvironment_environment_name(typeName, envName);
		return results.stream().map(a -> toDTO(a, 2)).collect(Collectors.toList());
	}
	

	/**
	 * Converts the passed environmentConfig to a DTO.
	 */
	public SubEnvironmentConfigDTO toDTO(SubEnvironmentConfig environmentConfig) {
		return toDTO(environmentConfig, 1);
	}

	/**
	 * Converts the passed environmentConfig to a DTO. The depth is used to
	 * control the amount of association you want. It also prevents potential
	 * infinite serialization cycles.
	 *
	 * @param environmentConfig
	 * @param depth
	 *            the depth of the serialization. A depth equals to 0, means no
	 *            x-to-one association will be serialized. A depth equals to 1
	 *            means that xToOne associations will be serialized. 2 means,
	 *            xToOne associations of xToOne associations will be serialized,
	 *            etc.
	 */
	public SubEnvironmentConfigDTO toDTO(SubEnvironmentConfig subEnvironmentConfig, int depth) {
		if (subEnvironmentConfig == null) {
			return null;
		}

		SubEnvironmentConfigDTO dto = new SubEnvironmentConfigDTO();

		dto.id = subEnvironmentConfig.getId();
		dto.parameter = subEnvironmentConfig.getParameter();
		dto.value = subEnvironmentConfig.getValue();
		// hide sensitive info if user doesn't have rights
		if (subEnvironmentConfig.IsSensitive() && !SecurityHelper.userCanViewSensitiveData())
			dto.value = "[SENSITIVE]";
		else
			dto.value = subEnvironmentConfig.getValue();

		
		dto.hieraAddress = subEnvironmentConfig.getHieraAddress();
		dto.notes = subEnvironmentConfig.getNotes();
		dto.sensitive = subEnvironmentConfig.IsSensitive();
		dto.arrayItem = subEnvironmentConfig.isArrayItem();
		
		if (depth-- > 0) {
			dto.subEnvironment = subEnvironmentDTOService.toDTO(subEnvironmentConfig.getSubEnvironment(), depth);
		}

		return dto;
	}

	/**
	 * Converts the passed dto to a EnvironmentConfig. Convenient for query by
	 * example.
	 */
	public SubEnvironmentConfig toEntity(SubEnvironmentConfigDTO dto) {
		return toEntity(dto, 1);
	}

	/**
	 * Converts the passed dto to a EnvironmentConfig. Convenient for query by
	 * example.
	 */
	public SubEnvironmentConfig toEntity(SubEnvironmentConfigDTO dto, int depth) {
		if (dto == null) {
			return null;
		}

		SubEnvironmentConfig subEnvironmentConfig = new SubEnvironmentConfig();

		subEnvironmentConfig.setId(dto.id);
		subEnvironmentConfig.setParameter(dto.parameter);
		subEnvironmentConfig.setValue(dto.value);
		subEnvironmentConfig.setHieraAddress(dto.hieraAddress);
		subEnvironmentConfig.setSensitive(dto.sensitive);
		subEnvironmentConfig.setNotes(dto.notes);
		subEnvironmentConfig.setArrayItem(dto.arrayItem);
		
		if (depth-- > 0) {
			subEnvironmentConfig.setSubEnvironment(subEnvironmentDTOService.toEntity(dto.subEnvironment, depth));
		}

		return subEnvironmentConfig;
	}
	
	@Transactional(readOnly = true)
    public List<SubEnvironmentConfigDTO> complete(String query, int maxResults) {
    	List<SubEnvironmentConfig> results = subEnvironmentConfigRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public PageResponse<SubEnvironmentConfigDTO> findAll(PageRequestByExample<SubEnvironmentConfigDTO> req) {

    	Example<SubEnvironmentConfig> example = null;
        SubEnvironmentConfig subEnvironmentConfig = toEntity(req.example);

        if (subEnvironmentConfig != null) {
            example = Example.of(subEnvironmentConfig);
        }

        Page<SubEnvironmentConfig> page;
        if (example != null) {
            page = subEnvironmentConfigRepository.findAll(example, req.toPageable());
        } else {
            page = subEnvironmentConfigRepository.findAll(req.toPageable());
        }

        List<SubEnvironmentConfigDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);

    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public SubEnvironmentConfigDTO save(SubEnvironmentConfigDTO dto) {

    	if (dto == null) {
            return null;
        }

        SubEnvironmentConfig subEnvironmentConfig;
		if (dto.isIdSet()) {
			subEnvironmentConfig = subEnvironmentConfigRepository.findOne(dto.id);
			if (!subEnvironmentConfig.IsSensitive()
					|| (subEnvironmentConfig.IsSensitive() && SecurityHelper.userCanWriteSensitiveData())) {
				// only ever set sensitive data if we are allowed to
				// value is set to [SENSITIVE] when retrieved from
				// the DB for the DTO - we don't want to overwrite
				// the real value.
				subEnvironmentConfig.setValue(dto.value);
				subEnvironmentConfig.setSensitive(dto.sensitive);
			}
		} else {
			subEnvironmentConfig = new SubEnvironmentConfig();
			// sensitive data can always be set here as it's a new item
			// if the user has rights they will have been checked in the
			// UI.
			subEnvironmentConfig.setValue(dto.value);
			subEnvironmentConfig.setSensitive(dto.sensitive);
		}
        

        subEnvironmentConfig.setParameter(dto.parameter);
        subEnvironmentConfig.setHieraAddress(dto.hieraAddress);
        subEnvironmentConfig.setNotes(dto.notes);
        subEnvironmentConfig.setArrayItem(dto.arrayItem);
        
        if (dto.subEnvironment == null) {
            subEnvironmentConfig.setSubEnvironment(null);
        } else {
            SubEnvironment subEnvironment = subEnvironmentConfig.getSubEnvironment();
            if (subEnvironment == null || (subEnvironment.getId().compareTo(dto.subEnvironment.id) != 0)) {
                subEnvironmentConfig.setSubEnvironment(subEnvironmentRepository.findOne(dto.subEnvironment.id));
            }
        }

        return toDTO(subEnvironmentConfigRepository.save(subEnvironmentConfig));

    }
	
}
