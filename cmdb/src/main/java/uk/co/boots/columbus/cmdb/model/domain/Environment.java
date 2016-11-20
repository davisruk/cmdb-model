package uk.co.boots.columbus.cmdb.model.domain;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
@Table(name = "cm_environment")
public class Environment implements Identifiable<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Environment.class.getName());

    // Raw attributes
    // -- [id] ------------------------
    @Column(name = "EnvironmentID", precision = 19)
    @GeneratedValue
    @Id
    private Long id;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "EnvironmentName", nullable = false, unique = true, length = 50)
    private String name;

    @JoinColumn(name = "ReleaseID")
    @ManyToOne
    private Release release;

    @ManyToMany (mappedBy="environments", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<Server> servers;
    

    @PreRemove
    private void removeServersFromEnvioronement() {
        for (Server s : servers) {
            s.getEnvironments().remove(this);
        }
    }
    
    public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	@Override
    public String entityClassName() {
        return Environment.class.getSimpleName();
    }

    @Override
	public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Environment id(Long id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [name] ------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Environment name(String name) {
        setName(name);
        return this;
    }

    public Release getRelease() {
        return release;
    }

    /**
     * Set the {@link #release} without adding this Environment instance on the passed {@link #release}
     */
    public void setRelease(Release release) {
        this.release = release;
    }

    public Environment release(Release release) {
        setRelease(release);
        return this;
    }

    /**
     * Apply the default values.
     */
    public Environment withDefaults() {
        return this;
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