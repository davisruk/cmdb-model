package uk.co.boots.columbus.cmdb.model.component.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import uk.co.boots.columbus.cmdb.model.packageinfo.domain.PackageInfo;

@StaticMetamodel(SolutionComponent.class)
public abstract class SolutionComponent_ {

    // Raw attributes
    public static volatile SingularAttribute<SolutionComponent, Long> id;
    public static volatile SingularAttribute<SolutionComponent, String> name;
    public static volatile SingularAttribute<SolutionComponent, String> version;

    // Many to one
    public static volatile SingularAttribute<SolutionComponent, PackageInfo> packageInfo;
}