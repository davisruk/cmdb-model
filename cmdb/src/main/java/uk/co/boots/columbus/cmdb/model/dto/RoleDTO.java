package uk.co.boots.columbus.cmdb.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RoleDTO {
	public Integer id;
	public String name;
	public List<UserDTO> users;
	
	@JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}
