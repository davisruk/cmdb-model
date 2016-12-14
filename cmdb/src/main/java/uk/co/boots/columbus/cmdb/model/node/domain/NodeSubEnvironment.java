package uk.co.boots.columbus.cmdb.model.node.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;


/**
 * The persistent class for the cm_node_subenvironment database table.
 * 
 */
@Entity
@Table(name="cm_node_subenvironment")
public class NodeSubEnvironment implements Serializable {
	private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Id
	@Column(name="NodeSubID")
	private Long id;

	//bi-directional many-to-one association to Node
	@ManyToOne
	@JoinColumn(name="SubEnvironmentID")
	private SubEnvironment subEnvironment;

	//bi-directional many-to-one association to Node
	@ManyToOne
	@JoinColumn(name="NodeID")
	private Node node;

	//bi-directional many-to-one association to NodeIP
	@OneToMany(mappedBy="nodeSubEnvironment", cascade = {CascadeType.REMOVE})
	private Set<NodeIP> nodeIPs;

	public NodeSubEnvironment() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Node getNode() {
		return this.node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Set<NodeIP> getNodeIPs() {
		return this.nodeIPs;
	}

	public void setNodeIPs(Set<NodeIP> nodeIPs) {
		this.nodeIPs = nodeIPs;
	}

	public NodeIP addNodeIP(NodeIP nodeIP) {
		getNodeIPs().add(nodeIP);
		nodeIP.setNodeSubEnvironment(this);
		return nodeIP;
	}

	public NodeIP removeNodeIP(NodeIP nodeIP) {
		getNodeIPs().remove(nodeIP);
		nodeIP.setNodeSubEnvironment(null);
		return nodeIP;
	}

	public SubEnvironment getSubEnvironment() {
		return subEnvironment;
	}

	public void setSubEnvironment(SubEnvironment subEnvironment) {
		this.subEnvironment = subEnvironment;
	}

}