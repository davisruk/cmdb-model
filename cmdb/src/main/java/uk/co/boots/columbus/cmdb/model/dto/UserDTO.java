package uk.co.boots.columbus.cmdb.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.domain.Role;

public class UserDTO {
	public Integer id;
	public String email;
	public Boolean enabled;
	public String password;
	public String userName;
	public List<RoleDTO> roles;

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
	
}
