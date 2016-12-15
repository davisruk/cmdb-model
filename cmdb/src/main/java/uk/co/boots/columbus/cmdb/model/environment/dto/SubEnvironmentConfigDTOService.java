package uk.co.boots.columbus.cmdb.model.environment.dto;

import java.util.ArrayList;
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
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseConfigDTO;

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
		for (SubEnvironmentConfig conf : cl) {
			addr = conf.getHieraAddress();
			value = conf.getValue();
			// find Parameter in Hieara Address and replace with Parametername
			if (addr != null) {
				addr = addr.replaceAll("\\{ParamName\\}", conf.getParameter());
				addr = addr.replaceAll("\\{ENVID\\}", conf.getSubEnvironment().getEnvironment().getName());
				conf.setHieraAddress(addr);
			}
			if (value != null) {
				value = value.replaceAll("\\{ParamName\\}", conf.getParameter());
				value = value.replaceAll("\\{ENVID\\}", conf.getSubEnvironment().getEnvironment().getName());
				conf.setValue(value);
			}
		}
	}

	public List<SubEnvironmentConfigDTO> getSubEnvironmentDTOsWithHieraAddressesForRecursiveReleaseItems(
			SubEnvironmentDTO seDTO, List<ReleaseConfigDTO> relConfList) {
	
		List<SubEnvironmentConfigDTO> subConList = new ArrayList<SubEnvironmentConfigDTO>();
		for (ReleaseConfigDTO rDTO : relConfList) {
			if (rDTO.recurseBySubEnv.booleanValue()) {
				SubEnvironmentConfigDTO dto = new SubEnvironmentConfigDTO();
				dto.hieraAddress = rDTO.hieraAddress.replaceAll("\\{ParamName\\}", rDTO.getParameter());
				dto.hieraAddress = dto.hieraAddress.replaceAll("\\{ENVID\\}", seDTO.environment.name);
				dto.value = rDTO.value.replaceAll("\\{ParamName\\}", rDTO.getParameter());
				dto.value = dto.value.replaceAll("\\{ENVID\\}", seDTO.environment.name);
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
	public List<SubEnvironmentConfigDTO> findBySubEnvironmentReleaseName(String relName) {
		List<SubEnvironmentConfig> results = subEnvironmentConfigRepository.findBySubEnvironment_Release_name(relName);
		buildHieraAddresses(results);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
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
		dto.hieraAddress = subEnvironmentConfig.getHieraAddress();
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
        } else {
            subEnvironmentConfig = new SubEnvironmentConfig();
        }

        subEnvironmentConfig.setParameter(dto.parameter);
        subEnvironmentConfig.setValue(dto.value);
        subEnvironmentConfig.setHieraAddress(dto.hieraAddress);

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
