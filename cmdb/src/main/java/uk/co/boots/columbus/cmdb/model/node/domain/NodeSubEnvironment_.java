package uk.co.boots.columbus.cmdb.model.node.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;

public class NodeSubEnvironment_ {
	public static volatile SingularAttribute<NodeSubEnvironment, Long> id;
	public static volatile SingularAttribute<NodeSubEnvironment, SubEnvironment> subEnvironment;
	public static volatile SingularAttribute<NodeSubEnvironment, Node> node;
	public static volatile SetAttribute<NodeSubEnvironment, NodeIP> nodeIPs;
    	
}
