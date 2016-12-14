package uk.co.boots.columbus.cmdb.model.node.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;


/**
 * The persistent class for the cm_vip database table.
 * 
 */
@Entity
@Table(name="cm_vip")
public class VIP extends Node implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue
	@Column(name="VIPID")
	private String vipId;

	@Column(name="VIPName")
	private String name;

	public VIP() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVipId() {
		return vipId;
	}

	public void setVipId(String vipId) {
		this.vipId = vipId;
	}

}