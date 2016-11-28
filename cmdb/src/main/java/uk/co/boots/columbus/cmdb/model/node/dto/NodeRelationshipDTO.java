package uk.co.boots.columbus.cmdb.model.node.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.node.domain.Protocol;

public class NodeRelationshipDTO {
    public Long id;
    public Long startPort;
    public Long endPort;
    public Protocol protocol;
   
    // Many To One
    public NodeDTO publishingNode;
    public NodeDTO consumingNode;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }

}
