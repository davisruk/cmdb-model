package uk.co.boots.columbus.cmdb.model.node.domain;

import javax.persistence.metamodel.SingularAttribute;

public class NodeIP_ {
    // Raw attributes
    public static volatile SingularAttribute<NodeIP, Long> id;
    public static volatile SingularAttribute<NodeIP, String> ipAddress;
    public static volatile SingularAttribute<NodeIP, IPType> ipType;
    // Many to One
    public static volatile SingularAttribute<NodeIP, NodeSubEnvironment> nodeSubEnvironment; 

}
