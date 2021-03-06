package uk.co.boots.columbus.cmdb.model.component.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.component.domain.SolutionComponent;
import uk.co.boots.columbus.cmdb.model.component.repository.SolutionComponentRepository;
import uk.co.boots.columbus.cmdb.model.core.domain.LockStrategy;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.packageinfo.domain.PackageInfo;
import uk.co.boots.columbus.cmdb.model.packageinfo.dto.PackageInfoDTOService;
import uk.co.boots.columbus.cmdb.model.packageinfo.repository.PackageInfoRepository;

/**
 * A simple DTO Facility for SolutionComponent.
 */
@Service
public class SolutionComponentDTOService {

    @Inject
    private SolutionComponentRepository solutionComponentRepository;
    @Inject
    private PackageInfoDTOService packageInfoDTOService;
    @Inject
    private PackageInfoRepository packageInfoRepository;
    @Inject
    private LockStrategy lockStrategy;

    @Transactional(readOnly = true)
    public SolutionComponentDTO findOne(Long id) {
        return toDTO(solutionComponentRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<SolutionComponentDTO> complete(String query, int maxResults) {
        List<SolutionComponent> results = solutionComponentRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<SolutionComponentDTO> findAll(PageRequestByExample<SolutionComponentDTO> req) {
        Example<SolutionComponent> example = null;
        SolutionComponent solutionComponent = toEntity(req.example);

        if (solutionComponent != null) {
            example = Example.of(solutionComponent);
        }

        Page<SolutionComponent> page;
        if (example != null) {
            page = solutionComponentRepository.findAll(example, req.toPageable());
        } else {
            page = solutionComponentRepository.findAll(req.toPageable());
        }

        List<SolutionComponentDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public SolutionComponentDTO save(SolutionComponentDTO dto) {
        if (dto == null) {
            return null;
        }

        SolutionComponent solutionComponent;
        if (dto.isIdSet()) {
            solutionComponent = solutionComponentRepository.findOne(dto.id);
            lockStrategy.lockEntity(solutionComponent, dto);
        } else {
            solutionComponent = new SolutionComponent();
        }

        solutionComponent.setName(dto.name);

        if (dto.packageInfo == null) {
            solutionComponent.setPackageInfo(null);
        } else {
            PackageInfo packageInfo = solutionComponent.getPackageInfo();
            if (packageInfo == null || (packageInfo.getId().compareTo(dto.packageInfo.id) != 0)) {
                solutionComponent.setPackageInfo(packageInfoRepository.findOne(dto.packageInfo.id));
            }
        }

        return toDTO(solutionComponentRepository.save(solutionComponent));
    }

    /**
     * Converts the passed solutionComponent to a DTO.
     */
    public SolutionComponentDTO toDTO(SolutionComponent solutionComponent) {
        return toDTO(solutionComponent, 1);
    }

    /**
     * Converts the passed solutionComponent to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param solutionComponent
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public SolutionComponentDTO toDTO(SolutionComponent solutionComponent, int depth) {
        if (solutionComponent == null) {
            return null;
        }

        SolutionComponentDTO dto = new SolutionComponentDTO();

        dto.id = solutionComponent.getId();
        dto.name = solutionComponent.getName();
        dto.version = solutionComponent.getVersion();
        if (depth-- > 0) {
            dto.packageInfo = packageInfoDTOService.toDTO(solutionComponent.getPackageInfo(), depth);
        }

        return dto;
    }

    /**
     * Converts the passed dto to a SolutionComponent.
     * Convenient for query by example.
     */
    public SolutionComponent toEntity(SolutionComponentDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a SolutionComponent.
     * Convenient for query by example.
     */
    public SolutionComponent toEntity(SolutionComponentDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        SolutionComponent solutionComponent = new SolutionComponent();

        solutionComponent.setId(dto.id);
        solutionComponent.setName(dto.name);
        if (depth-- > 0) {
            solutionComponent.setPackageInfo(packageInfoDTOService.toEntity(dto.packageInfo, depth));
        }

        return solutionComponent;
    }
}