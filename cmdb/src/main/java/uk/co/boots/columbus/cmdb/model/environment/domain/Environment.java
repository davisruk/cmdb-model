package uk.co.boots.columbus.cmdb.model.environment.domain;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.BaseEntity;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;


@Entity
@Table(name = "cm_environment")
@AttributeOverride(name = "id", column = @Column(name = "EnvironmentID"))
public class Environment extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Environment.class.getName());

    @NotEmpty
    @Size(max = 50)
    @Column(name = "EnvironmentName", nullable = false, unique = true, length = 50)
	private String name;

	//bi-directional many-to-one association to EnvironmentType
	@ManyToOne
	@JoinColumn(name="EnvironmentTypeID")
	private EnvironmentType environmentType;

	//bi-directional many-to-one association to SubEnvironment
	@OneToMany(mappedBy="environment", cascade = CascadeType.REMOVE)
	private List<SubEnvironment> subEnvironments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EnvironmentType getEnvironmentType() {
		return environmentType;
	}

	public void setEnvironmentType(EnvironmentType environmentType) {
		this.environmentType = environmentType;
	}

	public List<SubEnvironment> getSubEnvironments() {
		return subEnvironments;
	}

	public void setSubEnvironments(List<SubEnvironment> subEnvironments) {
		this.subEnvironments = subEnvironments;
	}

	@Override
	public String entityClassName() {
		return Environment.class.getSimpleName();
	}


    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Environment && hashCode() == other.hashCode());
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
     * Construct a readable string representation for this Environment instance.
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