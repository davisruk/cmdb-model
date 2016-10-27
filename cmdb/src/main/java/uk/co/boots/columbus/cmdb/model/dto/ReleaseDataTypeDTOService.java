/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
package uk.co.boots.columbus.cmdb.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.domain.ReleaseDataType;
import uk.co.boots.columbus.cmdb.model.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.repository.ReleaseDataTypeRepository;

/**
 * A simple DTO Facility for ReleaseDataType.
 */
@Service
public class ReleaseDataTypeDTOService {

    @Inject
    private ReleaseDataTypeRepository releaseDataTypeRepository;

    @Transactional(readOnly = true)
    public ReleaseDataTypeDTO findOne(Long id) {
        return toDTO(releaseDataTypeRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<ReleaseDataTypeDTO> complete(String query, int maxResults) {
        List<ReleaseDataType> results = releaseDataTypeRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<ReleaseDataTypeDTO> findAll(PageRequestByExample<ReleaseDataTypeDTO> req) {
        Example<ReleaseDataType> example = null;
        ReleaseDataType releaseDataType = toEntity(req.example);

        if (releaseDataType != null) {
            example = Example.of(releaseDataType);
        }

        Page<ReleaseDataType> page;
        if (example != null) {
            page = releaseDataTypeRepository.findAll(example, req.toPageable());
        } else {
            page = releaseDataTypeRepository.findAll(req.toPageable());
        }

        List<ReleaseDataTypeDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public ReleaseDataTypeDTO save(ReleaseDataTypeDTO dto) {
        if (dto == null) {
            return null;
        }

        ReleaseDataType releaseDataType;
        if (dto.isIdSet()) {
            releaseDataType = releaseDataTypeRepository.findOne(dto.id);
        } else {
            releaseDataType = new ReleaseDataType();
        }

        releaseDataType.setName(dto.name);

        return toDTO(releaseDataTypeRepository.save(releaseDataType));
    }

    /**
     * Converts the passed releaseDataType to a DTO.
     */
    public ReleaseDataTypeDTO toDTO(ReleaseDataType releaseDataType) {
        return toDTO(releaseDataType, 1);
    }

    /**
     * Converts the passed releaseDataType to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param releaseDataType
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public ReleaseDataTypeDTO toDTO(ReleaseDataType releaseDataType, int depth) {
        if (releaseDataType == null) {
            return null;
        }

        ReleaseDataTypeDTO dto = new ReleaseDataTypeDTO();

        dto.id = releaseDataType.getId();
        dto.name = releaseDataType.getName();
        if (depth-- > 0) {
        }

        return dto;
    }

    /**
     * Converts the passed dto to a ReleaseDataType.
     * Convenient for query by example.
     */
    public ReleaseDataType toEntity(ReleaseDataTypeDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a ReleaseDataType.
     * Convenient for query by example.
     */
    public ReleaseDataType toEntity(ReleaseDataTypeDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        ReleaseDataType releaseDataType = new ReleaseDataType();

        releaseDataType.setId(dto.id);
        releaseDataType.setName(dto.name);
        if (depth-- > 0) {
        }

        return releaseDataType;
    }
}