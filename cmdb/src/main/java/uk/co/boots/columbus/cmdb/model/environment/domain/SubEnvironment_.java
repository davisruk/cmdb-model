package uk.co.boots.columbus.cmdb.model.environment.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

import uk.co.boots.columbus.cmdb.model.node.domain.Node;
import uk.co.boots.columbus.cmdb.model.release.domain.Release;

public class SubEnvironment_ {
    // Raw attributes
    public static volatile SingularAttribute<SubEnvironment, Long> id;
    // Many to one
    public static volatile SingularAttribute<SubEnvironment, Release> release;

    public static volatile SingularAttribute<SubEnvironment, SubEnvironmentType> subEnvironmentType;

    public static volatile SingularAttribute<SubEnvironment, Environment> Environment;
    
    public static volatile ListAttribute<SubEnvironment, SubEnvironmentConfig> subEnvironmentConfigs;
    
    //Many to Many
    public static volatile ListAttribute<SubEnvironment, Node> nodes;
}
