package uk.co.boots.columbus.cmdb.model.server.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import uk.co.boots.columbus.cmdb.model.golbalconfig.domain.Globalconfig;

@StaticMetamodel(ServerConfig.class)
public abstract class ServerConfig_ {

    // Raw attributes
    public static volatile SingularAttribute<ServerConfig, Long> id;
    public static volatile SingularAttribute<ServerConfig, String> parameter;
    public static volatile SingularAttribute<ServerConfig, String> value;
    public static volatile SingularAttribute<ServerConfig, String> hieraAddress;
    public static volatile SingularAttribute<Globalconfig, String> notes;
    public static volatile SingularAttribute<Globalconfig, Boolean> sensitive;

    // Many to one
    public static volatile SingularAttribute<ServerConfig, Server> server;
}