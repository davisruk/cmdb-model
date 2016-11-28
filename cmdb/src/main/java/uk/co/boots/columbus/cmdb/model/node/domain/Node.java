package uk.co.boots.columbus.cmdb.model.node.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;
@Entity
@Table(name = "cm_node")
public class Node implements Identifiable<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Node.class.getName());
    
	@Column(name = "NodeID", precision = 19)
    @GeneratedValue
    @Id
	private Long id;

	@Column (name = "NodeType")
	@Enumerated(EnumType.STRING)
	private NodeType nodeType;
	
	@OneToMany(mappedBy="node")
	private List<NodeIP> nodeIPs;
	
	@OneToMany(mappedBy="publishingNode")
	private List<NodeRelationship> relationships;

	@OneToMany(mappedBy="node")
	private List<Server> servers;

	@ManyToMany (mappedBy="nodes", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<SubEnvironment> subEnvironments;

	public Node() {
		super();
		subEnvironments = new ArrayList<SubEnvironment>();
		servers = new ArrayList<Server>();
		relationships = new ArrayList<NodeRelationship>();
	}

	public Node(NodeType nodeType) {
		super();
		this.nodeType = nodeType;
		subEnvironments = new ArrayList<SubEnvironment>();
		servers = new ArrayList<Server>();
		relationships = new ArrayList<NodeRelationship>();
	}

	public List<NodeIP> getNodeIPs() {
		return nodeIPs;
	}

	public void setNodeIPs(List<NodeIP> nodeIPs) {
		this.nodeIPs = nodeIPs;
	}

	public List<NodeRelationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(List<NodeRelationship> relationships) {
		this.relationships = relationships;
	}

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
	}

	public List<SubEnvironment> getSubEnvironments() {
		return subEnvironments;
	}

	public void setSubEnvironments(List<SubEnvironment> subEnvironments) {
		this.subEnvironments = subEnvironments;
	}

	public Node withDefaults() {
        return this;
    }
    
    @Override
    public String entityClassName() {
        return Node.class.getSimpleName();
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	public void addSubEnvironment (SubEnvironment se){
		subEnvironments.add(se);
	}

	public void removeSubEnvironment (SubEnvironment se){
		subEnvironments.remove(se);
	}

	public void addServer (Server s){
		servers.add(s);
	}

	public void removeServer (Server s){
		servers.remove(s);
	}

	public void addRelationship (NodeRelationship rel){
		relationships.add(rel);
	}

	public void removeRelationship (NodeRelationship nodeRel){
		relationships.remove(nodeRel);
	}

	public void addNodeIP(NodeIP nodeIP){
		nodeIPs.add(nodeIP);
		nodeIP.setNode(this);
	}

	public void removeNodeIP (NodeIP nodeIP){
		nodeIPs.remove(nodeIP);
		nodeIP.setNode(null);
	}

	@Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Node && hashCode() == other.hashCode());
    }

    
    @Transient
    private volatile int previousHashCode = 0;

    @Override
    public int hashCode() {
        int hashCode = Objects.hashCode(id);

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
                .add("id", getId())
                .add("type", getNodeType())
                .toString();
    }
}
