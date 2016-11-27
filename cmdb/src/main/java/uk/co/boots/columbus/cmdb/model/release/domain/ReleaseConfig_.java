package uk.co.boots.columbus.cmdb.model.release.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ReleaseConfig.class)
public abstract class ReleaseConfig_ {

    // Raw attributes
    public static volatile SingularAttribute<ReleaseConfig, Long> id;
    public static volatile SingularAttribute<ReleaseConfig, String> parameter;
    public static volatile SingularAttribute<ReleaseConfig, String> value;
    public static volatile SingularAttribute<ReleaseConfig, String> hieraAddress;

    // Many to one
    public static volatile SingularAttribute<ReleaseConfig, Release> my_release;
}