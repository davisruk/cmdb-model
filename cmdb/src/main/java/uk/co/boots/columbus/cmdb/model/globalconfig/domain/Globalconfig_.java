package uk.co.boots.columbus.cmdb.model.globalconfig.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Globalconfig.class)
public abstract class Globalconfig_ {

    // Raw attributes
    public static volatile SingularAttribute<Globalconfig, Long> id;
    public static volatile SingularAttribute<Globalconfig, String> parameter;
    public static volatile SingularAttribute<Globalconfig, String> value;
    public static volatile SingularAttribute<Globalconfig, String> hieraAddress;
    public static volatile SingularAttribute<Globalconfig, Boolean> recursiveByEnv;
    public static volatile SingularAttribute<Globalconfig, Boolean> recursiveByRel;
    public static volatile SingularAttribute<Globalconfig, Boolean> recursiveBySubEnv;
    public static volatile SingularAttribute<Globalconfig, String> notes;
    public static volatile SingularAttribute<Globalconfig, Boolean> sensitive;
    public static volatile SingularAttribute<Globalconfig, Boolean> arrayItem;
}