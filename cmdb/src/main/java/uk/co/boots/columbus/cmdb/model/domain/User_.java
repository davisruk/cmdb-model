package uk.co.boots.columbus.cmdb.model.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public abstract class User_ {
    // Raw attributes
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Boolean> enabled;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile ListAttribute<User, Role> roles;
}
