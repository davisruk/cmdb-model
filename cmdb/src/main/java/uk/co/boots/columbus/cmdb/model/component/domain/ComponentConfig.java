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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.common.base.MoreObjects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.core.domain.IdentifiableHashBuilder;
import uk.co.boots.columbus.cmdb.model.core.domain.LockableEntity;

@Entity
@Table(name = "cm_componentconfig")
public class ComponentConfig implements Identifiable<Long>, LockableEntity, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ComponentConfig.class.getName());

    @Column(name = "CompConfigID", precision = 19)
    @GeneratedValue
    @Id
    private Long id;

    @Size(max = 255)
    @Column(name = "CompConfigParameter", length = 255)
    private String parameter;

    @Size(max = 255)
    @Column(name = "CompConfigValue", length = 255)
    private String value;
    
    @Size(max = 255)
    @Column(name = "CompConfigHieraAddress", length = 255)
    private String hieraAddress;

	@Column(name = "CompConfigNotes")
    private String notes;

    @Column(name = "CompConfigIsSensitive")
    private Boolean sensitive;
    
    @NotNull
    @JoinColumn(name = "ComponentID", nullable = false)
    @ManyToOne
    private SolutionComponent solutionComponent;

    @Column(name="Version")
    @Version
    private Long version;
    
    @Transient
    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();
    
    @Override
    public String entityClassName() {
        return ComponentConfig.class.getSimpleName();
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

    // -- [value] ------------------------

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // -- [hieraAddress] ------------------------

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
    // many-to-one: ComponentConfig.my_component ==> SolutionComponent.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    public SolutionComponent getSolutionComponent() {
        return solutionComponent;
    }

    /**
     * Set the {@link #solutionComponent} without adding this ComponentConfig instance on the passed {@link #solutionComponent}
     */
    public void setSolutionComponent(SolutionComponent solutionComponent) {
        this.solutionComponent = solutionComponent;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof ComponentConfig && hashCode() == other.hashCode());
    }

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this, parameter + value + hieraAddress);
    }

    /**
     * Construct a readable string representation for this ComponentConfig instance.
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

	public Boolean IsSensitive() {
		return sensitive;
	}

	public void setSensitive(Boolean sensitive) {
		this.sensitive = sensitive;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}