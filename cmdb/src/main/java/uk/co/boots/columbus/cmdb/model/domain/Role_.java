package uk.co.boots.columbus.cmdb.model.domain;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public abstract class Role_ {
	    // Raw attributes
	    public static volatile SingularAttribute<User, Integer> id;
	    public static volatile SingularAttribute<User, String> name;
	    public static volatile CollectionAttribute<Role, User> users;
}