package uk.co.boots.columbus.cmdb.model.node.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the cm_vip database table.
 * 
 */
@Entity
@Table(name="cm_vip")
public class VIP implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue
	@Id
	@Column(name="VIPID")
	private String id;

	@Column(name="VIPName")
	private String name;

	//bi-directional many-to-one association to Node
	@ManyToOne
	@JoinColumn(name="NodeID")
	private Node node;

	public VIP() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getNode() {
		return this.node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

}