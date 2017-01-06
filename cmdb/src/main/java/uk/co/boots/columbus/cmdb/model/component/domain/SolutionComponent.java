/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/domain/Entity.java.e.vm
 */
package uk.co.boots.columbus.cmdb.model.component.domain;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.core.domain.IdentifiableHashBuilder;
import uk.co.boots.columbus.cmdb.model.packageinfo.domain.PackageInfo;

@Entity
@Table(name = "cm_component")
public class SolutionComponent implements Identifiable<Long>, Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SolutionComponent.class.getName());

	// Raw attributes
	private Long id;
	private String name;

	// Many to one
	private PackageInfo packageInfo;

	@Override
	public String entityClassName() {
		return SolutionComponent.class.getSimpleName();
	}

	// -- [id] ------------------------

	@Override
	@Column(name = "ComponentID", precision = 19)
	@GeneratedValue
	@Id
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	@Transient
	public boolean isIdSet() {
		return id != null;
	}
	// -- [name] ------------------------

	@NotEmpty
	@Size(max = 50)
	@Column(name = "ComponentName", nullable = false, length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// -----------------------------------------------------------------
	// Many to One support
	// -----------------------------------------------------------------

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// many-to-one: SolutionComponent.packageInfo ==> PackageInfo.id
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	@JoinColumn(name = "PackageID")
	@ManyToOne
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