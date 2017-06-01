package uk.co.boots.columbus.cmdb.model.release.dto;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.core.dto.support.FilterMetadata;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.environment.domain.Environment;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.golbalconfig.domain.Globalconfig;
import uk.co.boots.columbus.cmdb.model.node.domain.Node;
import uk.co.boots.columbus.cmdb.model.release.domain.Release;
import uk.co.boots.columbus.cmdb.model.release.domain.ReleaseConfig;
import uk.co.boots.columbus.cmdb.model.release.repository.ReleaseConfigRepository;
import uk.co.boots.columbus.cmdb.model.release.repository.ReleaseRepository;
import uk.co.boots.columbus.cmdb.model.security.util.SecurityHelper;

@Service
public class ReleaseConfigDTOService {
	@Inject
	private ReleaseConfigRepository releaseConfigRepository;
	@Inject
	private ReleaseDTOService releaseDTOService;
	@Inject
	private ReleaseRepository releaseRepository;

	@Transactional(readOnly = true)
	public ReleaseConfigDTO findOne(Long id) {
		return toDTO(releaseConfigRepository.findOne(id));
	}

	private void buildHieraAddresses(List<ReleaseConfig> cl, String envName) {
		String addr;
		String value;
		boolean allowSensitive = SecurityHelper.userCanViewSensitiveData();
		for (ReleaseConfig conf : cl) {
			addr = conf.getHieraAddress();
			value = conf.getValue();
			if (addr != null) {
				addr = addr.replaceAll("\\{Release\\}", conf.getRelease().getName());
				addr = addr.replaceAll("\\{ParamName\\}", conf.getParameter());
				addr = addr.replaceAll("\\{ENVID\\}", envName);
				conf.setHieraAddress(addr);
			}
			if (value != null) {
				value = value.replaceAll("\\{Release\\}", conf.getRelease().getName());
				value = value.replaceAll("\\{ParamName\\}", conf.getParameter());
				value = value.replaceAll("\\{ENVID\\}", envName);
				if (!allowSensitive && conf.IsSensitive()){
					value = "[SENSITIVE]";
				}
				conf.setValue(value);
			}
		}
	}

	@Transactional(readOnly = true)
	public List<ReleaseConfigDTO> findByReleaseName(String relName) {
		List<ReleaseConfig> results = releaseConfigRepository.findByRelease_name(relName);
		buildHieraAddresses(results, relName);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public List<ReleaseConfigDTO> getDistinctConfigsForEnv(String envName) {
		List<ReleaseConfig> results = releaseConfigRepository
				.findDistinctByRelease_subEnvironments_environment_name(envName);
		buildHieraAddresses(results, envName);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<ReleaseConfigDTO> complete(String query, int maxResults) {
		List<ReleaseConfig> results = releaseConfigRepository.complete(query, maxResults);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public PageResponse<ReleaseConfigDTO> findAll(PageRequestByExample<ReleaseConfigDTO> req) {
		Example<ReleaseConfig> example = null;
		Page<ReleaseConfig> page;
		if (req.example == null)
			page = releaseConfigRepository.findAll(req.toPageable());
		else
		{
			ReleaseConfig rc = toEntity(req.example);
			example = Example.of(rc);			
			if (rc != null) {
				if (req.lazyLoadEvent != null && req.lazyLoadEvent.filters != null && req.lazyLoadEvent.filters.size() > 0){
					// build the Matcher for this page request
					// probably a little overkill but should cope with all use cases
					ExampleMatcher matcher = ExampleMatcher.matching();
					for (Map.Entry<String, FilterMetadata> entry : req.lazyLoadEvent.filters.entrySet()){
						FilterMetadata filter = entry.getValue();
						// setup the matcher for contains / starts with or ends with
						 matcher = matcher.withMatcher(entry.getKey(), match->filter.getMatcher(match));
					}
					example = Example.of(rc, matcher);
				}
			}
			page = releaseConfigRepository.findAll(example, req.toPageable());
		}

		List<ReleaseConfigDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
		return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
		
		
	}

	/**
	 * Save the passed dto as a new entity or update the corresponding entity if
	 * any.
	 */
	@Transactional
	public ReleaseConfigDTO save(ReleaseConfigDTO dto) {
		if (dto == null) {
			return null;
		}

		ReleaseConfig releaseConfig;
		if (dto.isIdSet()) {
			releaseConfig = releaseConfigRepository.findOne(dto.id);
			if (!releaseConfig.IsSensitive()
					|| (releaseConfig.IsSensitive() && SecurityHelper.userCanWriteSensitiveData())) {
				// only ever set sensitive data if we are allowed to
				// value is set to [SENSITIVE] when retrieved from
				// the DB for the DTO - we don't want to overwrite
				// the real value.
				releaseConfig.setValue(dto.value);
				releaseConfig.setSensitive(dto.sensitive);
			}
		} else {
			releaseConfig = new ReleaseConfig();
			// sensitive data can always be set here as it's a new item
			// if the user has rights they will have been checked in the
			// UI.
			releaseConfig.setValue(dto.value);
			releaseConfig.setSensitive(dto.sensitive);
		}

		releaseConfig.setParameter(dto.parameter);
		releaseConfig.setHieraAddress(dto.hieraAddress);
		releaseConfig.setRecursiveByEnv(dto.recursiveByEnv);
		releaseConfig.setRecursiveBySubEnv(dto.recursiveBySubEnv);
		releaseConfig.setNotes(dto.notes);

		if (dto.release == null) {
			releaseConfig.setRelease(null);
		} else {
			Release release = releaseConfig.getRelease();
			if (release == null || (release.getId().compareTo(dto.release.id) != 0)) {
				releaseConfig.setRelease(releaseRepository.findOne(dto.release.id));
			}
		}

		return toDTO(releaseConfigRepository.save(releaseConfig));
	}

	/**
	 * Converts the passed releaseConfig to a DTO.
	 */
	public ReleaseConfigDTO toDTO(ReleaseConfig releaseConfig) {
		return toDTO(releaseConfig, 1);
	}

	/**
	 * Converts the passed releaseConfig to a DTO. The depth is used to control
	 * the amount of association you want. It also prevents potential infinite
	 * serialization cycles.
	 *
	 * @param releaseConfig
	 * @param depth
	 *            the depth of the serialization. A depth equals to 0, means no
	 *            x-to-one association will be serialized. A depth equals to 1
	 *            means that xToOne associations will be serialized. 2 means,
	 *            xToOne associations of xToOne associations will be serialized,
	 *            etc.
	 */
	public ReleaseConfigDTO toDTO(ReleaseConfig releaseConfig, int depth) {
		if (releaseConfig == null) {
			return null;
		}

		ReleaseConfigDTO dto = new ReleaseConfigDTO();

		dto.id = releaseConfig.getId();
		dto.parameter = releaseConfig.getParameter();
		dto.value = releaseConfig.getValue();
		// hide sensitive info if user doesn't have rights
		if (releaseConfig.IsSensitive() && !SecurityHelper.userCanViewSensitiveData())
			dto.value = "[SENSITIVE]";
		else
			dto.value = releaseConfig.getValue();

		dto.hieraAddress = releaseConfig.getHieraAddress();
		dto.recursiveByEnv = releaseConfig.isRecursiveByEnv();
		dto.recursiveBySubEnv = releaseConfig.isRecursiveBySubEnv();
		dto.notes = releaseConfig.getNotes();
		dto.sensitive = releaseConfig.IsSensitive();

		if (depth-- > 0) {
			dto.release = releaseDTOService.toDTO(releaseConfig.getRelease(), depth);
		}

		return dto;
	}

	/**
	 * Converts the passed dto to a ReleaseConfig. Convenient for query by
	 * example.
	 */
	public ReleaseConfig toEntity(ReleaseConfigDTO dto) {
		return toEntity(dto, 1);
	}

	/**
	 * Converts the passed dto to a ReleaseConfig. Convenient for query by
	 * example.
	 */
	public ReleaseConfig toEntity(ReleaseConfigDTO dto, int depth) {
		if (dto == null) {
			return null;
		}

		ReleaseConfig releaseConfig = new ReleaseConfig();

		releaseConfig.setId(dto.id);
		releaseConfig.setParameter(dto.parameter);
		releaseConfig.setValue(dto.value);
		releaseConfig.setHieraAddress(dto.hieraAddress);
		releaseConfig.setRecursiveByEnv(dto.recursiveByEnv);
		releaseConfig.setRecursiveBySubEnv(dto.recursiveBySubEnv);
		releaseConfig.setSensitive(dto.sensitive);
		releaseConfig.setNotes(dto.notes);

		if (depth-- > 0) {
			releaseConfig.setRelease(releaseDTOService.toEntity(dto.release, depth));
		}

		return releaseConfig;
	}
}
