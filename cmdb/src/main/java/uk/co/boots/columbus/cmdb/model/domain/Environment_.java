/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/domain/EntityMeta_.java.e.vm
 */
package uk.co.boots.columbus.cmdb.model.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Environment.class)
public abstract class Environment_ {

    // Raw attributes
    public static volatile SingularAttribute<Environment, Long> id;
    public static volatile SingularAttribute<Environment, String> name;

    // Many to one
    public static volatile SingularAttribute<Environment, Release> release;
}