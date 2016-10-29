package uk.co.boots.columbus.cmdb.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
}
