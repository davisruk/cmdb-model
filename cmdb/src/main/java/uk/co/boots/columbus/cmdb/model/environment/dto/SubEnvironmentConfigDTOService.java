package uk.co.boots.columbus.cmdb.model.environment.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public SubEnvironmentConfigDTO findOne(Long id) {
		return toDTO(subEnvironmentConfigRepository.findOne(id));
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
}
