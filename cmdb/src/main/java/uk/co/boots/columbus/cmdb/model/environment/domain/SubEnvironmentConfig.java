package uk.co.boots.columbus.cmdb.model.environment.domain;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.MoreObjects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.core.domain.IdentifiableHashBuilder;

@Entity
@Table(name = "cm_subenvironmentconfig")
@NamedQuery(name = "SubEnvironmentConfig.findAll", query = "SELECT s FROM SubEnvironmentConfig s")
public class SubEnvironmentConfig implements Identifiable<Long>, Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SubEnvironmentConfig.class.getName());
	
	@Column(name = "SubEnvConfigID", precision = 19)
	@GeneratedValue
	@Id
	private Long id;

	// @NotEmpty
	// @Size(max = 50)
	@Column(name = "SubEnvConfigHieraAddress", nullable = false, unique = true, length = 50)
	private String hieraAddress;
	// @NotEmpty
	// @Size(max = 50)
	@Column(name = "SubEnvConfigParameter", nullable = false, unique = true, length = 50)
	private String parameter;
	// @NotEmpty
	// @Size(max = 50)
	@Column(name = "SubEnvConfigValue", nullable = false, unique = true, length = 50)
	private String value;

	// bi-directional many-to-one association to SubEnvironment
	@ManyToOne
	@JoinColumn(name = "SubEnvironmentID")
	private SubEnvironment subEnvironment;

	public SubEnvironmentConfig() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHieraAddress() {
		return hieraAddress;
	}

	public void setHieraAddress(String address) {
		this.hieraAddress = address;
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

	public SubEnvironment getSubEnvironment() {
		return subEnvironment;
	}

	public void setSubEnvironment(SubEnvironment subEnvironment) {
		this.subEnvironment = subEnvironment;
	}
	@Override
	public String entityClassName() {
		return SubEnvironmentConfig.class.getSimpleName();
	}

	// -- [id] ------------------------

	@Override
	@Transient
	public boolean isIdSet() {
		return id != null;
	}

	/**
	 * Apply the default values.
	 */
	public SubEnvironmentConfig withDefaults() {
		return this;
	}

	/**
	 * Equals implementation using a business key.
	 */
	@Override
	public boolean equals(Object other) {
		return this == other || (other instanceof SubEnvironmentConfig && hashCode() == other.hashCode());
	}

	private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

	@Override
	public int hashCode() {
		return identifiableHashBuilder.hash(log, this, parameter + value + hieraAddress);
	}

	/**
	 * Construct a readable string representation for this EnvironmentConfig
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
