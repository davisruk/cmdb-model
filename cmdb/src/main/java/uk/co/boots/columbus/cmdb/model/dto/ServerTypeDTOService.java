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

import uk.co.boots.columbus.cmdb.model.domain.ServerType;
import uk.co.boots.columbus.cmdb.model.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.repository.ServerTypeRepository;

/**
 * A simple DTO Facility for ServerType.
 */
@Service
public class ServerTypeDTOService {

    @Inject
    private ServerTypeRepository serverTypeRepository;

    @Transactional(readOnly = true)
    public ServerTypeDTO findOne(Long id) {
        return toDTO(serverTypeRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<ServerTypeDTO> complete(String query, int maxResults) {
        List<ServerType> results = serverTypeRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<ServerTypeDTO> findAll(PageRequestByExample<ServerTypeDTO> req) {
        Example<ServerType> example = null;
        ServerType serverType = toEntity(req.example);

        if (serverType != null) {
            example = Example.of(serverType);
        }

        Page<ServerType> page;
        if (example != null) {
            page = serverTypeRepository.findAll(example, req.toPageable());
        } else {
            page = serverTypeRepository.findAll(req.toPageable());
        }

        List<ServerTypeDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public ServerTypeDTO save(ServerTypeDTO dto) {
        if (dto == null) {
            return null;
        }

        ServerType serverType;
        if (dto.isIdSet()) {
            serverType = serverTypeRepository.findOne(dto.id);
        } else {
            serverType = new ServerType();
        }

        serverType.setName(dto.name);

        return toDTO(serverTypeRepository.save(serverType));
    }

    /**
     * Converts the passed serverType to a DTO.
     */
    public ServerTypeDTO toDTO(ServerType serverType) {
        return toDTO(serverType, 1);
    }

    /**
     * Converts the passed serverType to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param serverType
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public ServerTypeDTO toDTO(ServerType serverType, int depth) {
        if (serverType == null) {
            return null;
        }

        ServerTypeDTO dto = new ServerTypeDTO();

        dto.id = serverType.getId();
        dto.name = serverType.getName();
        if (depth-- > 0) {
        }

        return dto;
    }

    /**
     * Converts the passed dto to a ServerType.
     * Convenient for query by example.
     */
    public ServerType toEntity(ServerTypeDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a ServerType.
     * Convenient for query by example.
     */
    public ServerType toEntity(ServerTypeDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        ServerType serverType = new ServerType();

        serverType.setId(dto.id);
        serverType.setName(dto.name);
        if (depth-- > 0) {
        }

        return serverType;
    }
}