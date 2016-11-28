package uk.co.boots.columbus.cmdb.model.node.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.node.domain.IPType;

public class NodeIPDTO {
    public Long id;
    public String ipAddress;
    public IPType ipType;
    public NodeDTO node; 
    
    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }

}
