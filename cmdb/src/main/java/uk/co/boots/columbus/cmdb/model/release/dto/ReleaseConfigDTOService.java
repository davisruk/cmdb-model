package uk.co.boots.columbus.cmdb.model.release.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.release.domain.Release;
import uk.co.boots.columbus.cmdb.model.release.domain.ReleaseConfig;
import uk.co.boots.columbus.cmdb.model.release.repository.ReleaseConfigRepository;
import uk.co.boots.columbus.cmdb.model.release.repository.ReleaseRepository;

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

    private void buildHieraAddresses (List<ReleaseConfig> cl, String relName){
    	String addr;
    	String value;
    	for (ReleaseConfig conf: cl){
        	addr = conf.getHieraAddress();
        	value = conf.getValue();
        	if (addr != null){
	        	addr = addr.replaceAll("\\{Release\\}",relName);
	        	addr = addr.replaceAll("\\{ParamName\\}",conf.getParameter());
	        	addr = addr.replaceAll("\\{ENVID\\}", conf.getRelease().getName());
	        	conf.setHieraAddress(addr);
        	}
        	if (value != null){
	        	value = value.replaceAll("\\{Release\\}",relName);
	        	value = value.replaceAll("\\{ParamName\\}",conf.getParameter());
	        	value = value.replaceAll("\\{ENVID\\}",conf.getRelease().getName());
	        	conf.setValue(value);
        	}
        }
    }
    @Transactional(readOnly = true)
    public List<ReleaseConfigDTO> findByReleaseName(String relName) {
        List<ReleaseConfig> results = releaseConfigRepository.findByRelease_name(relName);
        buildHieraAddresses (results, relName);
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
        ReleaseConfig releaseConfig = toEntity(req.example);

        if (releaseConfig != null) {
            example = Example.of(releaseConfig);
        }

        Page<ReleaseConfig> page;
        if (example != null) {
            page = releaseConfigRepository.findAll(example, req.toPageable());
        } else {
            page = releaseConfigRepository.findAll(req.toPageable());
        }

        List<ReleaseConfigDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public ReleaseConfigDTO save(ReleaseConfigDTO dto) {
        if (dto == null) {
            return null;
        }

        ReleaseConfig releaseConfig;
        if (dto.isIdSet()) {
            releaseConfig = releaseConfigRepository.findOne(dto.id);
        } else {
            releaseConfig = new ReleaseConfig();
        }

        releaseConfig.setParameter(dto.parameter);
        releaseConfig.setValue(dto.value);
        releaseConfig.setHieraAddress(dto.hieraAddress);

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
     * Converts the passed releaseConfig to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param releaseConfig
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public ReleaseConfigDTO toDTO(ReleaseConfig releaseConfig, int depth) {
        if (releaseConfig == null) {
            return null;
        }

        ReleaseConfigDTO dto = new ReleaseConfigDTO();

        dto.id = releaseConfig.getId();
        dto.parameter = releaseConfig.getParameter();
        dto.value = releaseConfig.getValue();
        dto.hieraAddress = releaseConfig.getHieraAddress();
        if (depth-- > 0) {
            dto.release = releaseDTOService.toDTO(releaseConfig.getRelease(), depth);
        }

        return dto;
    }

    /**
     * Converts the passed dto to a ReleaseConfig.
     * Convenient for query by example.
     */
    public ReleaseConfig toEntity(ReleaseConfigDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a ReleaseConfig.
     * Convenient for query by example.
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
        if (depth-- > 0) {
            releaseConfig.setRelease(releaseDTOService.toEntity(dto.release, depth));
        }

        return releaseConfig;
    }}
