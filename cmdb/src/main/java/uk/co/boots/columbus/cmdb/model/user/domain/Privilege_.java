package uk.co.boots.columbus.cmdb.model.user.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Privilege.class)
public abstract class Privilege_ {
	    // Raw attributes
	    public static volatile SingularAttribute<User, Integer> id;
	    public static volatile SingularAttribute<User, String> name;
	    public static volatile SetAttribute<Role, User> roles;
}
