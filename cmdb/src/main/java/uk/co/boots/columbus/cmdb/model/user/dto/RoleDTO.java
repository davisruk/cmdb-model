package uk.co.boots.columbus.cmdb.model.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RoleDTO {
	public Integer id;
	public String name;
	public List<UserDTO> users;
	public List<PrivilegeDTO> privileges;
	
	@JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }

	@Override
	public String toString() {
		return "RoleDTO [id=" + id + ", name=" + name + ", users=" + users + ", privileges=" + privileges + "]";
	}
	
	public void addUser(UserDTO user) {
		if (users == null)
			users = new ArrayList<UserDTO>();
		users.add(user);
	}

	public void addPrivilege(PrivilegeDTO priv) {
		if (privileges == null)
			privileges = new ArrayList<PrivilegeDTO>();
		privileges.add(priv);
	}
	
}
