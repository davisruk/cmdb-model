package uk.co.boots.columbus.cmdb.model.globalconfig.domain;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.metamodel.SingularAttribute;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.core.domain.IdentifiableHashBuilder;

@Entity
@Table(name = "cm_globalconfig")

public class Globalconfig implements Identifiable<Long>, Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Globalconfig.class.getName());

	@Column(name = "GlobalConfigID", precision = 19)
	@GeneratedValue
	@Id
	private Long id;

	@NotEmpty
	@Size(max = 255)
	@Column(name = "GlobalConfigParameter", nullable = false, length = 255)
	private String parameter;

	@Size(max = 255)
	@Column(name = "GlobalConfigValue", length = 255)
	private String value;

	@Size(max = 255)
	@Column(name = "GlobalConfigHieraAddress", length = 255)
	private String hieraAddress;

	@Column(name = "RecursiveByEnv")
	private Boolean recursiveByEnv;

	@Column(name = "RecursiveByRel")
	private Boolean recursiveByRel;

	@Column(name = "RecursiveBySubEnv")
	private Boolean recursiveBySubEnv;

	@Column(name = "GlobalConfigNotes")
	private String notes;

	@Column(name = "GlobalConfigIsSensitive")
	private Boolean sensitive;

	@Transient
	private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

	@Override
	public String entityClassName() {
		return Globalconfig.class.getSimpleName();
	}

	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public boolean isIdSet() {
		return id != null;
	}

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

	/**
	 * Equals implementation using a business key.
	 */
	@Override
	public boolean equals(Object other) {
		return this == other || (other instanceof Globalconfig && hashCode() == other.hashCode());
	}

	@Override
	public int hashCode() {
		return identifiableHashBuilder.hash(log, this, parameter + value + hieraAddress);
	}

	/**
	 * Construct a readable string representation for this Globalconfig
	 * instance.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	@Transient
	public String toString() {
		return MoreObjects.toStringHelper(this) //
				.add("id", getId()) //
				.add("parameter", getParameter()) //
				.add("value", getValue()) //
				.add("hieraAddress", getHieraAddress()) //
				.toString();
	}

	public Boolean isRecursiveByEnv() {
		return recursiveByEnv;
	}

	public void setRecursiveByEnv(Boolean recursiveByEnv) {
		this.recursiveByEnv = recursiveByEnv;
	}

	public Boolean isRecursiveByRel() {
		return recursiveByRel;
	}

	public void setRecursiveByRel(Boolean recursiveByRel) {
		this.recursiveByRel = recursiveByRel;
	}

	public Boolean isRecursiveBySubEnv() {
		return recursiveBySubEnv;
	}

	public void setRecursiveBySubEnv(Boolean recursiveBySubEnv) {
		this.recursiveBySubEnv = recursiveBySubEnv;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean IsSensitive() {
		return sensitive;
	}

	public void setSensitive(Boolean sensitive) {
		this.sensitive = sensitive;
	}
}