package uk.co.boots.columbus.cmdb.model.environment.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTO;

public class SubEnvironmentDTO {
    public Long id;
    public ReleaseDTO release;
    public SubEnvironmentTypeDTO subEnvironmentType;
    public EnvironmentDTO environment;
    public List<SubEnvironmentConfigDTO> subEnvironmentConfigs;
    public List<ServerDTO> servers;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }

}
