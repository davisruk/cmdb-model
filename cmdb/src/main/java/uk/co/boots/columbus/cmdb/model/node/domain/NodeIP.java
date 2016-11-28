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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;

@Entity
@Table(name = "cm_nodeip")
public class NodeIP implements Identifiable<Long>, Serializable {

	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(NodeIP.class.getName());
	
	@Column(name = "NodeIPID", precision = 19)
    @GeneratedValue
    @Id
	private Long id;

	@Column (name = "NodeIPType")
	@Enumerated(EnumType.STRING)
	private IPType ipType;
    
    @NotEmpty
    @Size(max = 50)
    @Column(name = "NodeIPAddress", nullable = false, unique = true, length = 50)
	private String ipAddress;
	
	public NodeIP(IPType ipType, String ipAddress) {
		super();
		this.ipType = ipType;
		this.ipAddress = ipAddress;
	}

	public NodeIP(IPType ipType, String ipAddress, Node node) {
		super();
		this.ipType = ipType;
		this.ipAddress = ipAddress;
		this.node = node;
	}


    public String getIpAddress() {
		return ipAddress;
	}

	//bi-directional many-to-one association to EnvironmentType
	@ManyToOne
	@JoinColumn(name="NodeID")
	private Node node;
   
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public NodeIP() {
		super();
	}

	public NodeIP withDefaults() {
        return this;
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

	public IPType getIPType() {
		return ipType;
	}

	public void setIPType(IPType ipType) {
		this.ipType = ipType;
	}

	@Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof NodeIP && hashCode() == other.hashCode());
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
