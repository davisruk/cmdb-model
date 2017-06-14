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

	@Column(name = "RelConfigID", precision = 19)
	@GeneratedValue
	@Id
	private Long id;

	@Column(name = "RelConfigParameter")
	private String parameter;

	@Column(name = "RelConfigValue")
	private String value;

	@Column(name = "RelConfigHieraAddress")
	private String hieraAddress;

	@Column(name = "RecursiveByEnv")
	private Boolean recursiveByEnv;

	@Column(name = "RecursiveBySubEnv")
	private Boolean recursiveBySubEnv;

	@Column(name = "ReleaseConfigNotes")
	private String notes;

	@Column(name = "ReleaseConfigIsSensitive")
	private Boolean sensitive;

	@Column(name = "isArrayItem")
	private Boolean arrayItem;

	@NotNull
	@JoinColumn(name = "ReleaseID", nullable = false)
	@ManyToOne
	private Release release;

	@Transient
	private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

	@Override
	public String entityClassName() {
		return ReleaseConfig.class.getSimpleName();
	}

	// -- [id] ------------------------

	@Override
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
	// -- [parameter] ------------------------

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getHieraAddress() {
		return hieraAddress;
	}

	public void setHieraAddress(String hieraAddress) {
		this.hieraAddress = hieraAddress;
	}

	// -----------------------------------------------------------------
	// Many to One support
	// -----------------------------------------------------------------

	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// many-to-one: ReleaseConfig.release ==> Release.id
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	public Release getRelease() {
		return release;
	}

	/**
	 * Set the {@link #release} without adding this ReleaseConfig instance on
	 * the passed {@link #release}
	 */
	public void setRelease(Release release) {
		this.release = release;
	}

	public Boolean isRecursiveByEnv() {
		return recursiveByEnv;
	}

	public void setRecursiveByEnv(Boolean recursiveByEnv) {
		this.recursiveByEnv = recursiveByEnv;
	}

	public Boolean isRecursiveBySubEnv() {
		return recursiveBySubEnv;
	}

	public void setRecursiveBySubEnv(Boolean recursiveBySubEnv) {
		this.recursiveBySubEnv = recursiveBySubEnv;
	}

	public Boolean IsSensitive() {
		return sensitive;
	}

	public void setSensitive(Boolean sensitive) {
		this.sensitive = sensitive;
	}

	/**
	 * Equals implementation using a business key.
	 */
	@Override
	public boolean equals(Object other) {
		return this == other || (other instanceof ReleaseConfig && hashCode() == other.hashCode());
	}

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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean isArrayItem() {
		return arrayItem;
	}

	public void setArrayItem(Boolean arrayItem) {
		this.arrayItem = arrayItem;
	}
	
}
