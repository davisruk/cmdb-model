package uk.co.boots.columbus.cmdb.model.user.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.CascadeType;

import uk.co.boots.columbus.cmdb.model.component.domain.ComponentConfig;
import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.core.domain.IdentifiableHashBuilder;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table (name = "role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Identifiable<Integer>, Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ComponentConfig.class.getName());
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="roles")
	private List<User> users;

	//bi-directional many-to-many association to Role
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(
		name="role_privilege"
		, joinColumns={
			@JoinColumn(name="role_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="privilege_id")
			}
		)
	private Set<Privilege> privileges;
	
	public Role() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Transient 
	private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();
    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this, name);
    }

	@Override
	public String entityClassName() {
		return Role.class.getSimpleName();
	}

	@Override
	public boolean isIdSet() {
		 return id != null;
	}
	
	public void addUser(User user) {
		if (users == null)
			users = new ArrayList<User>();
		users.add(user);
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", users=" + users + ", identifiableHashBuilder="
				+ identifiableHashBuilder + "]";
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	
}