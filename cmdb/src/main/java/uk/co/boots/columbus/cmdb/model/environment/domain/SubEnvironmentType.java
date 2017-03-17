package uk.co.boots.columbus.cmdb.model.environment.domain;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.BaseEntity;


@Entity
@Table(name="cm_subenvironmenttype")
@NamedQuery(name="SubEnvironmentType.findAll", query="SELECT s FROM SubEnvironmentType s")
@AttributeOverride(name = "id", column = @Column(name = "SubEnvironmentTypeID"))
public class SubEnvironmentType extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(SubEnvironmentType.class.getName());

    //  @NotEmpty
//  @Size(max = 50)
	@Column(name = "SubEnvironmentTypeName", nullable = false, unique = true, length = 50)
	private String name;

	//bi-directional many-to-one association to SubEnvironment
	@OneToMany(mappedBy="subEnvironmentType")
	private List<SubEnvironment> subEnvironments;

	public SubEnvironmentType() {
	}


	public SubEnvironment addSubEnvironment(SubEnvironment cmSubenvironment) {
		getSubEnvironments().add(cmSubenvironment);
		cmSubenvironment.setSubEnvironmentType(this);

		return cmSubenvironment;
	}

	public SubEnvironment removeSubEnvironment(SubEnvironment cmSubenvironment) {
		getSubEnvironments().remove(cmSubenvironment);
		cmSubenvironment.setSubEnvironmentType(null);

		return cmSubenvironment;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<SubEnvironment> getSubEnvironments() {
		return subEnvironments;
	}


	public void setSubEnvironments(List<SubEnvironment> subEnvironments) {
		this.subEnvironments = subEnvironments;
	}
	
    @Override
    public String entityClassName() {
        return SubEnvironmentType.class.getSimpleName();
    }
    
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof SubEnvironmentType && hashCode() == other.hashCode());
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
     * Construct a readable string representation for this ServerType instance.
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