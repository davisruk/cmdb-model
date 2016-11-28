package uk.co.boots.columbus.cmdb.model.node.domain;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
@Entity
@Table(name = "cm_noderelationship")
public class NodeRelationship implements Identifiable<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(NodeRelationship.class.getName());
    
	@Column(name = "NodeRelationshipID", precision = 19)
    @GeneratedValue
    @Id
	private Long id;

	@Column(name = "startPort", precision = 19)
    @Id
	private Long startPort;

	@Column(name = "endPort", precision = 19)
    @Id
	private Long endPort;

	//many-to-one association to EnvironmentType
	@ManyToOne
	@JoinColumn(name="PublishingNodeID")
	private Node publishingNode;

	//many-to-one association to EnvironmentType
	@ManyToOne
	@JoinColumn(name="ConsumingNodeID")
	private Node consumingNode;

	@Column (name = "Protocol")
	@Enumerated(EnumType.STRING)
	private Protocol protocol;

	public NodeRelationship(Long startPort, Long endPort, Node publishingNode, Node consumingNode, Protocol protocol) {
		super();
		this.startPort = startPort;
		this.endPort = endPort;
		this.publishingNode = publishingNode;
		this.consumingNode = consumingNode;
		this.protocol = protocol;
	}

	@Override
    public String entityClassName() {
        return NodeIP.class.getSimpleName();
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof NodeRelationship && hashCode() == other.hashCode());
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
                .add("type", getId())
                .toString();
    }
}
