package uk.co.boots.columbus.cmdb.model.environment.domain;

import javax.persistence.metamodel.SingularAttribute;

import uk.co.boots.columbus.cmdb.model.golbalconfig.domain.Globalconfig;

public class SubEnvironmentConfig_ {
    public static volatile SingularAttribute<EnvironmentConfig, Long> id;
    public static volatile SingularAttribute<EnvironmentConfig, String> parameter;
    public static volatile SingularAttribute<EnvironmentConfig, String> value;
    public static volatile SingularAttribute<EnvironmentConfig, String> hieraAddress;
    public static volatile SingularAttribute<Globalconfig, String> notes;
    public static volatile SingularAttribute<Globalconfig, Boolean> sensitive;
    

    // Many to one
    public static volatile SingularAttribute<SubEnvironmentConfig, SubEnvironment> subEnvironment;


}
