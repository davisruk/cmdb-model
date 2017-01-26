package uk.co.boots.columbus.cmdb.model.node.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the cm_nodeiprelationship database table.
 * 
 */
@Entity
@Table(name="cm_nodeiprelationship")
public class NodeIPRelationship implements Serializable {
	private static final long serialVersionUID = 1L;

    @GeneratedValue
	@Id
	@Column(name="RelationshipID")
	private String id;
	
	@Column(name = "EndPort", precision = 19)
	private Long endPort;

	@Column (name = "Protocol")
	@Enumerated(EnumType.STRING)
	private Protocol protocol;

	@Column(name = "StartPort", precision = 19)
	private Long startPort;

	@ManyToOne
	@JoinColumn(name="PublishingNodeIPID")
	private NodeIP publishingNodeIP;

	@ManyToOne
	@JoinColumn(name="ConsumingNodeIPID")
	private NodeIP consumingNodeIP;

	public NodeIPRelationship() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getEndPort() {
		return this.endPort;
	}

	public void setEndPort(Long endPort) {
		this.endPort = endPort;
	}

	public Protocol getProtocol() {
		return this.protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public Long getStartPort() {
		return this.startPort;
	}

	public void setStartPort(Long startPort) {
		this.startPort = startPort;
	}

	public NodeIP getPublishingNodeIP() {
		return publishingNodeIP;
	}

	public void setPublishingNodeIP(NodeIP publishingNode) {
		this.publishingNodeIP = publishingNode;
	}

	public NodeIP getConsumingNodeIP() {
		return consumingNodeIP;
	}

	public void setConsumingNodeIP(NodeIP consumingNode) {
		this.consumingNodeIP = consumingNode;
	}
}