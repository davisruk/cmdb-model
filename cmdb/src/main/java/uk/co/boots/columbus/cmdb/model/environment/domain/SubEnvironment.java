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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.release.domain.Release;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;
import uk.co.boots.columbus.cmdb.model.server.domain.ServerType;

@Entity
@Table(name = "cm_subenvironment")
public class SubEnvironment implements Identifiable<Long>, Serializable{
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(SubEnvironment.class.getName());
	
	@Column(name = "SubEnvironmentID", precision = 19)
	@GeneratedValue
	@Id 
	private Long id;

	//bi-directional many-to-one association to Environment
	@ManyToOne
	@JoinColumn(name="EnvironmentID")
	private Environment environment;

	//bi-directional many-to-one association to Release
	@ManyToOne
	@JoinColumn(name="ReleaseID")
	private Release release;

	//bi-directional many-to-many association to Server
	@ManyToMany
	@JoinTable(
		name="cm_server_subenvironment"
		, joinColumns={
			@JoinColumn(name="SubEnvironmentID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ServerID")
			}
		)
	private List<Server> servers;

	//bi-directional many-to-one association to SubEnvironmentType
	@ManyToOne
	@JoinColumn(name="SubEnvironmentTypeID")
	private SubEnvironmentType subEnvironmentType;

	//bi-directional many-to-one association to SubEnvironmentConfig
	@OneToMany(mappedBy="subEnvironment")
	private List<SubEnvironmentConfig> subEnvironmentConfigs;

	public SubEnvironment() {
	}

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Release getRelease() {
		return release;
	}



	public void setRelease(Release release) {
		this.release = release;
	}



	public Environment getEnvironment() {
		return this.environment;
	}

	public void setEnvironment(Environment cmEnvironment) {
		this.environment = cmEnvironment;
	}

	public List<Server> getServers() {
		return this.servers;
	}

	public void setServers(List<Server> cmServers) {
		this.servers = cmServers;
	}

	public SubEnvironmentType getSubEnvironmentType() {
		return this.subEnvironmentType;
	}

	public void setSubEnvironmentType(SubEnvironmentType cmSubenvironmenttype) {
		this.subEnvironmentType = cmSubenvironmenttype;
	}

	public List<SubEnvironmentConfig> getSubEnvironmentConfigs() {
		return this.subEnvironmentConfigs;
	}

	public void setSubEnvironmentConfigs(List<SubEnvironmentConfig> cmSubenvironmentconfigs) {
		this.subEnvironmentConfigs = cmSubenvironmentconfigs;
	}

	public SubEnvironmentConfig addSubEnvironmentConfig(SubEnvironmentConfig cmSubenvironmentconfig) {
		getSubEnvironmentConfigs().add(cmSubenvironmentconfig);
		cmSubenvironmentconfig.setSubEnvironment(this);

		return cmSubenvironmentconfig;
	}

	public SubEnvironmentConfig removeSubEnvironmentConfig(SubEnvironmentConfig cmSubenvironmentconfig) {
		getSubEnvironmentConfigs().remove(cmSubenvironmentconfig);
		cmSubenvironmentconfig.setSubEnvironment(null);

		return cmSubenvironmentconfig;
	}
    /**
     * Apply the default values.
     */
    public SubEnvironment withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof SubEnvironment && hashCode() == other.hashCode());
    }

    private volatile int previousHashCode = 0;

    @Override
    public int hashCode() {
        int hashCode = Objects.hashCode(getId());

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
                .toString();
    }
    
    @Override
    public String entityClassName() {
        return SubEnvironment.class.getSimpleName();
    }
    
    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }    
}