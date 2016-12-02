package uk.co.boots.columbus.cmdb.model.environment.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.node.dto.NodeDTO;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseDTO;

public class SubEnvironmentDTO {
    public Long id;
    public ReleaseDTO release;
    public SubEnvironmentTypeDTO subEnvironmentType;
    public EnvironmentDTO environment;
    public List<SubEnvironmentConfigDTO> subEnvironmentConfigs;
    public List<NodeDTO> nodes;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }

}
