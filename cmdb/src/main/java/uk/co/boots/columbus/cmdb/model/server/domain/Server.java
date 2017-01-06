package uk.co.boots.columbus.cmdb.model.server.domain;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.node.domain.Node;

@Entity
@Table(name = "cm_server")
public class Server extends Node implements Identifiable<Long>, Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(Server.class.getName());

	// Raw attributes
	@Column(name = "ServerID", precision = 19)
	@GeneratedValue
	// @Id
	private Long serverId;

	@NotEmpty
	@Size(max = 50)
	@Column(name = "ServerName", nullable = false, unique = true, length = 50)
	private String name;

	@JoinColumn(name = "ServerTypeID")
	@ManyToOne
	private ServerType serverType;

	@OneToMany(mappedBy = "server")
	private List<ServerConfig> serverConfigs;

	/*
	 * @JoinColumn(name = "NodeID")
	 * 
	 * @ManyToOne private Node node;
	 */
	/*
	 * @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE,
	 * CascadeType.PERSIST })
	 * 
	 * @JoinTable( name="cm_server_environment" , joinColumns={
	 * 
	 * @JoinColumn(name="ServerID") } , inverseJoinColumns={
	 * 
	 * @JoinColumn(name="EnvironmentID") } ) private List<Environment>
	 * environments;
	 */
	// bi-directional many-to-many association to SubEnvironment
	// @ManyToMany(mappedBy="servers")
	// private List<SubEnvironment> subEnvironments;

	public Server(String name, ServerType serverType) {
		super();
		this.name = name;
		this.serverType = serverType;
	}

	public Server() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String entityClassName() {
		return Server.class.getSimpleName();
	}

	// -- [id] ------------------------

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long id) {
		this.serverId = id;
	}

	@Override
	@Transient
	public boolean isIdSet() {
		return serverId != null;
	}
	// -- [name] ------------------------

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ServerType getServerType() {
		return serverType;
	}

	/**
	 * Set the {@link #serverType} without adding this Server instance on the
	 * passed {@link #serverType}
	 */
	public void setServerType(ServerType serverType) {
		this.serverType = serverType;
	}

	/*
	 * public List<SubEnvironment> getSubEnvironments() { return
	 * subEnvironments; }
	 * 
	 * public void setSubEnvironments(List<SubEnvironment> subEnvironments) {
	 * this.subEnvironments = subEnvironments; }
	 */

	/**
	 * Apply the default values.
	 */
	public Server withDefaults() {
		return this;
	}

	/**
	 * Equals implementation using a business key.
	 */
	@Override
	public boolean equals(Object other) {
		return this == other || (other instanceof Server && hashCode() == other.hashCode());
	}

	@Transient
	private volatile int previousHashCode = 0;

	@Override
	public int hashCode() {
		int hashCode = Objects.hashCode(getName());

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
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this) //
				.add("id", getId()) //
				.add("name", getName()) //
				.toString();
	}
}