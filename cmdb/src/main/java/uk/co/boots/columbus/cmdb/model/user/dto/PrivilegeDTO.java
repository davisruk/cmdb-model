package uk.co.boots.columbus.cmdb.model.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PrivilegeDTO {
	public Integer id;
	public String name;
	public List<RoleDTO> roles;
	
	@JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }

	@Override
	public String toString() {
		return "PrivilegeDTO [id=" + id + ", name=" + name + ", roles=" + roles + "]";
	}
	
	public void addRole(RoleDTO role) {
		if (roles == null)
			roles = new ArrayList<RoleDTO>();
		roles.add(role);
	}


}
