package uk.co.boots.columbus.cmdb.model.user.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public abstract class Role_ {
	    // Raw attributes
	    public static volatile SingularAttribute<User, Integer> id;
	    public static volatile SingularAttribute<User, String> name;
	    public static volatile ListAttribute<Role, User> users;
	    public static volatile SetAttribute<Role, Privilege> privileges;
}
