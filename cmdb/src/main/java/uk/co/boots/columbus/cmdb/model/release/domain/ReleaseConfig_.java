package uk.co.boots.columbus.cmdb.model.release.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import uk.co.boots.columbus.cmdb.model.globalconfig.domain.Globalconfig;

@StaticMetamodel(ReleaseConfig.class)
public abstract class ReleaseConfig_ {

    // Raw attributes
    public static volatile SingularAttribute<ReleaseConfig, Long> id;
    public static volatile SingularAttribute<ReleaseConfig, String> parameter;
    public static volatile SingularAttribute<ReleaseConfig, String> value;
    public static volatile SingularAttribute<ReleaseConfig, String> hieraAddress;
    public static volatile SingularAttribute<ReleaseConfig, Boolean> recursiveByEnv;
    public static volatile SingularAttribute<ReleaseConfig, Boolean> recursiveBySubEnv;
    public static volatile SingularAttribute<Globalconfig, String> notes;
    public static volatile SingularAttribute<Globalconfig, Boolean> sensitive;
    public static volatile SingularAttribute<Globalconfig, Boolean> arrayItem;
    
    // Many to one
    public static volatile SingularAttribute<ReleaseConfig, Release> my_release;
}