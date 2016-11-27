package uk.co.boots.columbus.cmdb.model.environment.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTO;

/**
 * Simple DTO for Environment.
 */
public class EnvironmentDTO {
    public Long id;
    public String name;
    public List<ServerDTO> servers;
    
    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}