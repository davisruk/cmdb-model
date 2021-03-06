/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/domain/EntityMeta_.java.e.vm
 */
package uk.co.boots.columbus.cmdb.model.component.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import uk.co.boots.columbus.cmdb.model.golbalconfig.domain.Globalconfig;

@StaticMetamodel(ComponentConfig.class)
public abstract class ComponentConfig_ {

    // Raw attributes
    public static volatile SingularAttribute<ComponentConfig, Long> id;
    public static volatile SingularAttribute<ComponentConfig, String> parameter;
    public static volatile SingularAttribute<ComponentConfig, String> value;
    public static volatile SingularAttribute<ComponentConfig, String> hieraAddress;
    public static volatile SingularAttribute<ComponentConfig, String> notes;
    public static volatile SingularAttribute<ComponentConfig, Boolean> sensitive;
    public static volatile SingularAttribute<ComponentConfig, Long> version;

    // Many to one
    public static volatile SingularAttribute<ComponentConfig, SolutionComponent> solutionComponent;
}