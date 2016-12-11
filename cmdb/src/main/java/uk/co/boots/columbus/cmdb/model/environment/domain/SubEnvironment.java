package uk.co.boots.columbus.cmdb.model.environment.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.node.domain.Node;
import uk.co.boots.columbus.cmdb.model.node.domain.NodeSubEnvironment;
import uk.co.boots.columbus.cmdb.model.release.domain.Release;

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

	//bi-directional many-to-one association to SubEnvironmentType
	@ManyToOne
	@JoinColumn(name="SubEnvironmentTypeID")
	private SubEnvironmentType subEnvironmentType;

	//bi-directional many-to-one association to SubEnvironmentConfig
	@OneToMany(mappedBy="subEnvironment")
	private List<SubEnvironmentConfig> subEnvironmentConfigs;

	//bi-directional many-to-one association to SubEnvironmentConfig
	@OneToMany(mappedBy="subEnvironment")
	private Set<NodeSubEnvironment> nodeSubEnvironments;

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

    @Transient
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

	public Set<NodeSubEnvironment> getNodeSubEnvironments() {
		return nodeSubEnvironments;
	}

	public void setNodeSubEnvironments(Set<NodeSubEnvironment> nodeSubEnvironments) {
		this.nodeSubEnvironments = nodeSubEnvironments;
	}
	
	public NodeSubEnvironment addNodeSubEnvironment (NodeSubEnvironment nse){
		this.nodeSubEnvironments.add(nse);
		nse.setSubEnvironment(this);
		return nse;
	}

	public NodeSubEnvironment removeNodeSubEnvironment (NodeSubEnvironment nse){
		this.nodeSubEnvironments.remove(nse);
		nse.setSubEnvironment(null);
		return nse;
	}
	
	public NodeSubEnvironment addNode (Node node){
		NodeSubEnvironment nse = new NodeSubEnvironment();
		if (nodeSubEnvironments == null)
			nodeSubEnvironments = new HashSet<NodeSubEnvironment>();

		nse.setSubEnvironment(this);
		node.addNodeSubEnvironment(nse);
		nodeSubEnvironments.add(nse);
		return nse;
	}

	public NodeSubEnvironment removeNode (Node node){
		Optional<NodeSubEnvironment> optional = nodeSubEnvironments.stream().filter(x -> {
			return x.getNode().getId().equals(node.getId());
			}).findFirst();
		if (optional.isPresent()){
			NodeSubEnvironment nse = optional.get();
			this.nodeSubEnvironments.remove(nse);
			nse.setSubEnvironment(null);
			nse.setNode(null);
			return nse;
		}
		return null;
	}
	
	
}