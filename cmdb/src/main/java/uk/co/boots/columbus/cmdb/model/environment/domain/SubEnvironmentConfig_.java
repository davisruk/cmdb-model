package uk.co.boots.columbus.cmdb.model.environment.domain;

import javax.persistence.metamodel.SingularAttribute;

public class SubEnvironmentConfig_ {
    public static volatile SingularAttribute<EnvironmentConfig, Long> id;
    public static volatile SingularAttribute<EnvironmentConfig, String> parameter;
    public static volatile SingularAttribute<EnvironmentConfig, String> value;
    public static volatile SingularAttribute<EnvironmentConfig, String> hieraAddress;

    // Many to one
    public static volatile SingularAttribute<SubEnvironmentConfig, SubEnvironment> subEnvironment;


}
