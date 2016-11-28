package uk.co.boots.columbus.cmdb.model.node.domain;

import javax.persistence.metamodel.SingularAttribute;

public class NodeRelationship_ {
    public static volatile SingularAttribute<NodeRelationship, Long> id;
    public static volatile SingularAttribute<NodeRelationship, Long> startPort;
    public static volatile SingularAttribute<NodeRelationship, Long> endPort;
    public static volatile SingularAttribute<NodeRelationship, Protocol> protocol;
   
    // Many To One
    public static volatile SingularAttribute<NodeRelationship, Node> publishingNode;
    public static volatile SingularAttribute<NodeRelationship, Node> consumingNode;
}
