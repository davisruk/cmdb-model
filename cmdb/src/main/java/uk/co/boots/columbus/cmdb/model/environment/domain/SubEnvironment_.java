package uk.co.boots.columbus.cmdb.model.environment.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

import uk.co.boots.columbus.cmdb.model.release.domain.Release;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;

public class SubEnvironment_ {
    // Raw attributes
    public static volatile SingularAttribute<SubEnvironment, Long> id;
    // Many to one
    public static volatile SingularAttribute<SubEnvironment, Release> release;

    public static volatile SingularAttribute<SubEnvironment, SubEnvironmentType> subEnvironmentType;

    public static volatile SingularAttribute<SubEnvironment, Environment> Environment;
    
    //Many to Many
    public static volatile ListAttribute<SubEnvironment, Server> servers;
}
