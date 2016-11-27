package uk.co.boots.columbus.cmdb.model.environment.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Environment.class)
public abstract class Environment_ {

    // Raw attributes
    public static volatile SingularAttribute<Environment, Long> id;
    public static volatile SingularAttribute<Environment, String> name;
    public static volatile SingularAttribute<Environment, EnvironmentType> environmentType;
    public static volatile ListAttribute<Environment, SubEnvironment> subEnvironments;
}