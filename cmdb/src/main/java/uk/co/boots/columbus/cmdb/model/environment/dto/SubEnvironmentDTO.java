package uk.co.boots.columbus.cmdb.model.environment.dto;

import java.util.List;

import uk.co.boots.columbus.cmdb.model.core.dto.BaseDTO;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTO;

public class SubEnvironmentDTO extends BaseDTO {

    public ReleaseDTO release;
    public SubEnvironmentTypeDTO subEnvironmentType;
    public EnvironmentDTO environment;
    public List<SubEnvironmentConfigDTO> subEnvironmentConfigs;
    public List<ServerDTO> servers;

}
