package uk.co.boots.columbus.cmdb.model.component.domain;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;

import uk.co.boots.columbus.cmdb.model.core.domain.BaseEntity;
import uk.co.boots.columbus.cmdb.model.core.domain.IdentifiableHashBuilder;
import uk.co.boots.columbus.cmdb.model.packageinfo.domain.PackageInfo;

@Entity
@Table(name = "cm_component")
@AttributeOverride(name = "id", column = @Column(name = "ComponentID"))
public class SolutionComponent extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SolutionComponent.class.getName());

	@NotEmpty
	@Size(max = 50)
	@Column(name = "ComponentName", nullable = false, length = 50)
	private String name;
	
	@JoinColumn(name = "PackageID")
	@ManyToOne
	private PackageInfo packageInfo;

	@Override
	public String entityClassName() {
		return SolutionComponent.class.getSimpleName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PackageInfo getPackageInfo() {
		return packageInfo;
	}

	/**
	 * Set the {@link #packageInfo} without adding this SolutionComponent
	 * instance on the passed {@link #packageInfo}
	 */
	public void setPackageInfo(PackageInfo packageInfo) {
		this.packageInfo = packageInfo;
	}

	/**
	 * Equals implementation using a business key.
	 */
	@Override
	public boolean equals(Object other) {
		return this == other || (other instanceof SolutionComponent && hashCode() == other.hashCode());
	}

	@Transient
	private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

	@Override
	public int hashCode() {
		return identifiableHashBuilder.hash(log, this, name);
	}

	/**
	 * Construct a readable string representation for this SolutionComponent
	 * instance.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this) //
				.add("id", getId()) //
				.add("name", getName()) //
				.toString();
	}
}