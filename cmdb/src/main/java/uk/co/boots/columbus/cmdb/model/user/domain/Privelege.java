package uk.co.boots.columbus.cmdb.model.user.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import uk.co.boots.columbus.cmdb.model.component.domain.ComponentConfig;
import uk.co.boots.columbus.cmdb.model.core.domain.Identifiable;
import uk.co.boots.columbus.cmdb.model.core.domain.IdentifiableHashBuilder;

@Entity
public class Privelege implements Identifiable<Integer>, Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ComponentConfig.class.getName());
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="priveleges")
	private Set<Role> roles;

	public Privelege() {
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

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setUsers(Set<Role> roles) {
		this.roles = roles;
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
	
	public void addRole(Role role) {
		if (roles == null)
			roles = new HashSet<Role>();
		roles.add(role);
	}

	@Override
	public String toString() {
		return "Privelege [id=" + id + ", name=" + name + ", roles=" + roles + ", identifiableHashBuilder="
				+ identifiableHashBuilder + "]";
	}
}