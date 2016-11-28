package uk.co.boots.columbus.cmdb.model.node.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;

public class Node_ {
    // Raw attributes
    public static volatile SingularAttribute<Node, Long> id;
    public static volatile SingularAttribute<Node, NodeType> nodeType; 
    
    // One to Many
    public static volatile ListAttribute<Node, NodeIP> nodeIPs;
    public static volatile ListAttribute<Node, NodeRelationship> relationships;
    public static volatile ListAttribute<Node, Server> servers;
    
    // Many to Many
    public static volatile ListAttribute<Node, SubEnvironment> subEnvironments;
}
