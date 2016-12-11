package uk.co.boots.columbus.cmdb.model.node.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;

@Entity
@Table(name = "cm_node")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="NodeType")
public abstract class Node implements Identifiable<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(Node.class.getName());
    
	@Column(name = "NodeID", precision = 19)
    @GeneratedValue
    @Id
	private Long id;

	
	@OneToMany(mappedBy="publishingNode")
	private Set<NodeRelationship> relationships;

	//bi-directional many-to-one association to NodeSubEnvironment
	@OneToMany(mappedBy="node")
	private Set<NodeSubEnvironment> nodeSubEnvironments;

	@OneToMany(mappedBy="node")
	private Set<VIP> vips;
	
	public Node() {
		super();
		relationships = new HashSet<NodeRelationship>();
	}

	public Node(NodeType nodeType) {
		super();
//		this.nodeType = nodeType;
		relationships = new HashSet<NodeRelationship>();
	}

	public Set<NodeRelationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(Set<NodeRelationship> relationships) {
		this.relationships = relationships;
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

/*	public NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}
*/
	public void addRelationship (NodeRelationship rel){
		relationships.add(rel);
	}

	public void removeRelationship (NodeRelationship nodeRel){
		relationships.remove(nodeRel);
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
//                .add("type", getNodeType())
                .toString();
    }

	public Set<VIP> getVips() {
		return vips;
	}

	public void setVips(Set<VIP> vips) {
		this.vips = vips;
	}
	
	
	public VIP removeVIP(VIP vip){
		this.vips.remove(vip);
		vip.setNode(null);
		return vip;
	}

	public VIP addVIP(VIP vip){
		this.vips.add(vip);
		vip.setNode(this);
		return vip;
	}
	
	public Node addNodeSubEnvironment(NodeSubEnvironment nse){
		nse.setNode(this);
		if (this.nodeSubEnvironments == null)
			this.nodeSubEnvironments = new HashSet<NodeSubEnvironment>();
		this.nodeSubEnvironments.add(nse);
		return this;
	}
}
