/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
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
import uk.co.boots.columbus.cmdb.model.release.repository.ReleaseRepository;

/**
 * A simple DTO Facility for Release.
 */
@Service
public class ReleaseDTOService {

    @Inject
    private ReleaseRepository releaseRepository;

    @Transactional(readOnly = true)
    public ReleaseDTO findOne(Long id) {
        return toDTO(releaseRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<ReleaseDTO> complete(String query, int maxResults) {
        List<Release> results = releaseRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<ReleaseDTO> findAll(PageRequestByExample<ReleaseDTO> req) {
        Example<Release> example = null;
        Release release = toEntity(req.example);

        if (release != null) {
            example = Example.of(release);
        }

        Page<Release> page;
        if (example != null) {
            page = releaseRepository.findAll(example, req.toPageable());
        } else {
            page = releaseRepository.findAll(req.toPageable());
        }

        List<ReleaseDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public ReleaseDTO save(ReleaseDTO dto) {
        if (dto == null) {
            return null;
        }

        Release release;
        if (dto.isIdSet()) {
            release = releaseRepository.findOne(dto.id);
        } else {
            release = new Release();
        }

        release.setName(dto.name);

        return toDTO(releaseRepository.save(release));
    }

    /**
     * Converts the passed release to a DTO.
     */
    public ReleaseDTO toDTO(Release release) {
        return toDTO(release, 1);
    }

    /**
     * Converts the passed release to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param release
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public ReleaseDTO toDTO(Release release, int depth) {
        if (release == null) {
            return null;
        }

        ReleaseDTO dto = new ReleaseDTO();

        dto.id = release.getId();
        dto.name = release.getName();
        if (depth-- > 0) {
        }

        return dto;
    }

    /**
     * Converts the passed dto to a Release.
     * Convenient for query by example.
     */
    public Release toEntity(ReleaseDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a Release.
     * Convenient for query by example.
     */
    public Release toEntity(ReleaseDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        Release release = new Release();

        release.setId(dto.id);
        release.setName(dto.name);
        if (depth-- > 0) {
        }

        return release;
    }
}