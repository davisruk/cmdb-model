package uk.co.boots.columbus.cmdb.model.environment.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironmentConfig;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironmentType;
import uk.co.boots.columbus.cmdb.model.node.domain.Node;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseDTO;

public class SubEnvironmentDTO {
    public Long id;
    public ReleaseDTO release;
    public SubEnvironmentType subEnvironmentType;
    public EnvironmentDTO Environment;
    public List<SubEnvironmentConfigDTO> subEnvironmentConfigs;
    public List<Node> nodes;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }

}
