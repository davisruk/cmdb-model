package uk.co.boots.columbus.cmdb.model.release.domain;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.base.MoreObjects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.core.domain.IdentifiableHashBuilder;

@Entity
@Table(name = "cm_releaseconfig")
public class ReleaseConfig implements Identifiable<Long>, Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ReleaseConfig.class.getName());

	// Raw attributes
	private Long id;
	private String parameter;
	private String value;
	private String hieraAddress;

	// Many to one
	private Release release;

	@Override
	public String entityClassName() {
		return ReleaseConfig.class.getSimpleName();
	}

	// -- [id] ------------------------

	@Override
	@Column(name = "RelConfigID", precision = 19)
	@GeneratedValue
	@Id
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public ReleaseConfig id(Long id) {
		setId(id);
		return this;
	}

	@Override
	@Transient
	public boolean isIdSet() {
		return id != null;
	}
	// -- [parameter] ------------------------

	@Size(max = 50)
	@Column(name = "RelConfigParameter", length = 50)
	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public ReleaseConfig parameter(String parameter) {
		setParameter(parameter);
		return this;
	}
	// -- [value] ------------------------

	@Size(max = 50)
	@Column(name = "RelConfigValue", length = 50)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ReleaseConfig value(String value) {
		setValue(value);
		return this;
	}
	// -- [hieraAddress] ------------------------

	@Size(max = 50)
	@Column(name = "RelConfigHieraAddress", length = 50)
	public String getHieraAddress() {
		return hieraAddress;
	}

	public void setHieraAddress(String hieraAddress) {
		this.hieraAddress = hieraAddress;
	}

	public ReleaseConfig hieraAddress(String hieraAddress) {
		setHieraAddress(hieraAddress);
		return this;
	}

	// -----------------------------------------------------------------
	// Many to One support
	// -----------------------------------------------------------------

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// many-to-one: ReleaseConfig.release ==> Release.id
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	@NotNull
	@JoinColumn(name = "ReleaseID", nullable = false)
	@ManyToOne
	public Release getRelease() {
		return release;
	}

	/**
	 * Set the {@link #release} without adding this ReleaseConfig
	 * instance on the passed {@link #release}
	 */
	public void setRelease(Release release) {
		this.release = release;
	}

	public ReleaseConfig release(Release release) {
		setRelease(release);
		return this;
	}

	/**
	 * Apply the default values.
	 */
	public ReleaseConfig withDefaults() {
		return this;
	}

	/**
	 * Equals implementation using a business key.
	 */
	@Override
	public boolean equals(Object other) {
		return this == other || (other instanceof ReleaseConfig && hashCode() == other.hashCode());
	}

	private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

	@Override
	public int hashCode() {
		return identifiableHashBuilder.hash(log, this, parameter + value + hieraAddress);
	}

	/**
	 * Construct a readable string representation for this ReleaseConfig
	 * instance.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this) //
				.add("id", getId()) //
				.add("parameter", getParameter()) //
				.add("value", getValue()) //
				.add("hieraAddress", getHieraAddress()) //
				.toString();
	}
}
