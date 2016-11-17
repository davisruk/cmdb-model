package uk.co.boots.columbus.cmdb.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Simple DTO for Environment.
 */
public class EnvironmentDTO {
    public Long id;
    public String name;
    public ReleaseDTO release;
    public List<ServerDTO> servers;
    
    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}