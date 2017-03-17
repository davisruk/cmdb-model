package uk.co.boots.columbus.cmdb.model.environment.domain;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.BaseEntity;
import uk.co.boots.columbus.cmdb.model.core.domain.IdentifiableHashBuilder;

@Entity
@Table(name="cm_environmenttype")
@NamedQuery(name="EnvironmentType.findAll", query="SELECT e FROM EnvironmentType e")
@AttributeOverride(name = "id", column = @Column(name = "EnvironmentTypeID"))
public class EnvironmentType extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(EnvironmentType.class.getName());

    @NotEmpty
    @Size(max = 50)
    @Column(name = "EnvironmentTypeName", nullable = false, unique = true, length = 50)
    private String name;

    @Override
    public String entityClassName() {
        return EnvironmentType.class.getSimpleName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        boolean ret = (this == other || (other instanceof EnvironmentType && hashCode() == other.hashCode())); 
    	return ret;
    }

	@Transient
	private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

	@Override
	public int hashCode() {
		return identifiableHashBuilder.hash(log, this, name + id + version);
	}
    
/*
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
*/
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

