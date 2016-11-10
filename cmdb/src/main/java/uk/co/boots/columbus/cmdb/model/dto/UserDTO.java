package uk.co.boots.columbus.cmdb.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO implements UserDetails {

	private static final long serialVersionUID = 2754485519963698516L;
	
	public Integer id;
	public String email;
	public Boolean enabled;
	public String password;
	public String userName;
	public List<RoleDTO> roles;

	public UserDTO(Integer id, String email, Boolean enabled, String password, String userName, List<RoleDTO> list) {
		super();
		this.id = id;
		this.email = email;
		this.enabled = enabled;
		this.password = password;
		this.userName = userName;
		this.roles = list;
	}

	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	@JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + ", enabled=" + enabled + ", password=" + password
				+ ", userName=" + userName + ", roles=" + roles + "]";
	}
	
	public void addRole(RoleDTO role){
		if (roles == null)
			roles = new ArrayList<RoleDTO>();
		roles.add(role);
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return mapToGrantedAuthorities();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
    
	private List<GrantedAuthority> mapToGrantedAuthorities() {
        return roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.name))
                .collect(Collectors.toList());
    }
}
