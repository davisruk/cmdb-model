package uk.co.boots.columbus.cmdb.model.environment.domain;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;


@Entity
@Table(name = "cm_environment")
public class Environment implements Identifiable<Long>, Serializable {

	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Environment.class.getName());
	@Column(name = "EnvironmentID", precision = 19)
	@GeneratedValue
	@Id 
	private Long id;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "EnvironmentName", nullable = false, unique = true, length = 50)
	private String name;

	//bi-directional many-to-one association to EnvironmentType
	@ManyToOne
	@JoinColumn(name="EnvironmentTypeID")
	private EnvironmentType environmentType;

	//bi-directional many-to-many association to Server
/*	
	@ManyToMany
	@JoinTable(
		name="cm_server_environment"
		, joinColumns={
			@JoinColumn(name="EnvironmentID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ServerID")
			}
		)
*/
	@Transient // change this later
	private List<Server> servers;
	
	//bi-directional many-to-one association to EnvironmentConfig
	@OneToMany(mappedBy="environment")
	private List<EnvironmentConfig> environmentConfigs;

	//bi-directional many-to-one association to SubEnvironment
	@OneToMany(mappedBy="environment")
	private List<SubEnvironment> subEnvironments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public List<EnvironmentConfig> getEnvironmentConfigs() {
		return environmentConfigs;
	}

	public void setEnvironmentConfigs(List<EnvironmentConfig> environmentConfigs) {
		this.environmentConfigs = environmentConfigs;
	}

	public List<SubEnvironment> getSubEnvironments() {
		return subEnvironments;
	}

	public void setSubEnvironments(List<SubEnvironment> subEnvironments) {
		this.subEnvironments = subEnvironments;
	}



	public EnvironmentConfig addEnvironmentConfig(EnvironmentConfig cmEnvironmentconfig) {
		getEnvironmentConfigs().add(cmEnvironmentconfig);
		cmEnvironmentconfig.setEnvironment(this);

		return cmEnvironmentconfig;
	}

	public EnvironmentConfig removeEnvironmentConfig(EnvironmentConfig cmEnvironmentconfig) {
		getEnvironmentConfigs().remove(cmEnvironmentconfig);
		cmEnvironmentconfig.setEnvironment(null);

		return cmEnvironmentconfig;
	}

	public SubEnvironment addSubEnvironment(SubEnvironment cmSubenvironment) {
		getSubEnvironments().add(cmSubenvironment);
		cmSubenvironment.setEnvironment(this);

		return cmSubenvironment;
	}

	public SubEnvironment removeSubEnvironment(SubEnvironment cmSubenvironment) {
		getSubEnvironments().remove(cmSubenvironment);
		cmSubenvironment.setEnvironment(null);

		return cmSubenvironment;
	}

	@Override
	public String entityClassName() {
		return Environment.class.getSimpleName();
	}

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    // -- [name] ------------------------


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