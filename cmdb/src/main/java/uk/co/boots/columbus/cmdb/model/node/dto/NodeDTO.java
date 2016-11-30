package uk.co.boots.columbus.cmdb.model.node.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.node.domain.NodeType;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTO;

public class NodeDTO {
    public Long id;
    public NodeType nodeType; 
    public List<NodeIPDTO> nodeIPs;
    public List<NodeRelationshipDTO> relationships;
    public List<ServerDTO> servers;
    public List<SubEnvironmentDTO> subEnvironments;
    
    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}
