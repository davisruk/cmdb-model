package uk.co.boots.columbus.cmdb.model.node.domain;

import javax.persistence.metamodel.SingularAttribute;

public class VIP_ {
    public static volatile SingularAttribute<VIP, Long> id;
    public static volatile SingularAttribute<VIP, String> name;
    // Many to One
    public static volatile SingularAttribute<VIP, Node> node; 

}
