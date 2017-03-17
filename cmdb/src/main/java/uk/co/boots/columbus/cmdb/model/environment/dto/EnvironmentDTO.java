package uk.co.boots.columbus.cmdb.model.environment.dto;

import java.util.List;

import uk.co.boots.columbus.cmdb.model.core.dto.BaseDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTO;

/**
 * Simple DTO for Environment.
 */
public class EnvironmentDTO extends BaseDTO {

    public String name;
    public EnvironmentTypeDTO type;
    public List<ServerDTO> servers;
    public List<SubEnvironmentDTO> subEnvironments;
    
}