/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/domain/Entity.java.e.vm
 */
package uk.co.boots.columbus.cmdb.model.packageinfo.domain;

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
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.release.domain.Release;
import uk.co.boots.columbus.cmdb.model.server.domain.ServerType;

@Entity
@Table(name = "cm_package")
public class PackageInfo implements Identifiable<Long>, Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(PackageInfo.class.getName());

	// Raw attributes
	private Long id;
	private String name;

	// Many to one
	private PackageType packageType;
	private ServerType serverType;
	private Release release;

	@Override
	public String entityClassName() {
		return PackageInfo.class.getSimpleName();
	}

	// -- [id] ------------------------

	@Override
	@Column(name = "PackageID", precision = 19)
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
	@Column(name = "PackageName", nullable = false, unique = true, length = 50)
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
	// many-to-one: PackageInfo.packageType ==> PackageType.id
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	@JoinColumn(name = "PackageTypeID")
	@ManyToOne
	public PackageType getPackageType() {
		return packageType;
	}

	/**
	 * Set the {@link #packageType} without adding this PackageInfo instance on
	 * the passed {@link #packageType}
	 */
	public void setPackageType(PackageType packageType) {
		this.packageType = packageType;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// many-to-one: PackageInfo.serverType ==> ServerType.id
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	@JoinColumn(name = "ServerTypeID")
	@ManyToOne
	public ServerType getServerType() {
		return serverType;
	}

	/**
	 * Set the {@link #serverType} without adding this PackageInfo instance on
	 * the passed {@link #serverType}
	 */
	public void setServerType(ServerType serverType) {
		this.serverType = serverType;
	}

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// many-to-one: PackageInfo.release ==> Release.id
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	@JoinColumn(name = "ReleaseID")
	@ManyToOne
	public Release getRelease() {
		return release;
	}

	/**
	 * Set the {@link #release} without adding this PackageInfo instance on the
	 * passed {@link #release}
	 */
	public void setRelease(Release release) {
		this.release = release;
	}

	/**
	 * Equals implementation using a business key.
	 */
	@Override
	public boolean equals(Object other) {
		return this == other || (other instanceof PackageInfo && hashCode() == other.hashCode());
	}

	@Transient
	private volatile int previousHashCode = 0;

	@Override
	public int hashCode() {
		int hashCode = Objects.hashCode(getName());

		if (previousHashCode != 0 && previousHashCode != hashCode) {
			log.warning("DEVELOPER: hashCode has changed!." //
					+ "If you encounter this message you should take the time to carefuly review equals/hashCode for: " //
					+ getClass().getCanonicalName());
		}

		previousHashCode = hashCode;
		return hashCode;
	}

	/**
	 * Construct a readable string representation for this PackageInfo instance.
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