package uk.co.boots.columbus.cmdb.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Identifiable<Integer>, Serializable {
	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(User.class.getName());	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String email;

	private Boolean enabled;

	private String password;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-many association to Role
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="user_role"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private List<Role> roles;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
	@Transient
	private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this, userName + password);
    }

	@Override
	public String entityClassName() {
		return User.class.getSimpleName();
	}

	@Override
	public boolean isIdSet() {
		 return id != null;
	}

	public void addRole(Role role){
		if (roles == null)
			roles = new ArrayList<Role>();
		roles.add(role);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", enabled=" + enabled + ", password=" + password + ", userName="
				+ userName + ", roles=" + roles + ", identifiableHashBuilder=" + identifiableHashBuilder + "]";
	}
	
	
}