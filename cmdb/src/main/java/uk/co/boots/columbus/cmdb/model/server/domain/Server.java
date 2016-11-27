package uk.co.boots.columbus.cmdb.model.server.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.environment.domain.Environment;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;

@Entity
@Table(name = "cm_server")
public class Server implements Identifiable<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Server.class.getName());

    // Raw attributes
    @Column(name = "ServerID", precision = 19)
    @GeneratedValue
    @Id
    private Long id;
 
    @NotEmpty
    @Size(max = 50)
    @Column(name = "ServerName", nullable = false, unique = true, length = 50)
    private String name;

    @JoinColumn(name = "ServerTypeID")
    @ManyToOne
    private ServerType serverType;
    
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(
		name="cm_server_environment"
		, joinColumns={
				@JoinColumn(name="ServerID")
		}
		, inverseJoinColumns={
				@JoinColumn(name="EnvironmentID")
		}
	)
    private List<Environment> environments;

	//bi-directional many-to-many association to SubEnvironment
	@ManyToMany(mappedBy="servers")
	private List<SubEnvironment> subEnvironments;

    @Override
    public String entityClassName() {
        return Server.class.getSimpleName();
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

    public Server id(Long id) {
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

    public Server name(String name) {
        setName(name);
        return this;
    }

    public ServerType getServerType() {
        return serverType;
    }

    /**
     * Set the {@link #serverType} without adding this Server instance on the passed {@link #serverType}
     */
    public void setServerType(ServerType serverType) {
        this.serverType = serverType;
    }

    public Server serverType(ServerType serverType) {
        setServerType(serverType);
        return this;
    }

    public List<Environment> getEnvironments() {
        return environments;
    }

    /**
     * Set the {@link #environment} without adding this Server instance on the passed {@link #environment}
     */
    public void setEnvironments(List<Environment> environments) {
        this.environments = environments;
    }

    public Server addEnvironment(Environment environment) {
    	if (this.environments == null)
    		environments = new ArrayList<Environment>();
    	this.environments.add(environment);
        return this;
    }

    public Server removeEnvironment(Environment environment) {
    	if (this.environments != null)
    		this.environments.remove(environment);
        return this;
    }
    
	public List<SubEnvironment> getSubEnvironments() {
		return subEnvironments;
	}

	public void setSubEnvironments(List<SubEnvironment> subEnvironments) {
		this.subEnvironments = subEnvironments;
	}
    

    /**
     * Apply the default values.
     */
    public Server withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Server && hashCode() == other.hashCode());
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
     * Construct a readable string representation for this Server instance.
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