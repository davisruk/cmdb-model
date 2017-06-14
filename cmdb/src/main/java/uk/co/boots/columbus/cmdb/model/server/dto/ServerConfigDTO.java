package uk.co.boots.columbus.cmdb.model.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.hiera.dto.CoreConfigDTO;

/**
 * Simple DTO for ServerConfig.
 */
public class ServerConfigDTO implements CoreConfigDTO{

    public Long id;
    public String parameter;
    public String value;
    public String hieraAddress;
    public String notes;
    public Boolean sensitive;
    public ServerDTO server;
    public Boolean arrayItem;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
    
    public Long getId() {
		return id;
	}

	public String getParameter() {
		return parameter;
	}

	public String getValue() {
		return value;
	}

	public String getHieraAddress() {
		return hieraAddress;
	}
	
	public Boolean isArrayItem(){
		return arrayItem;
	}
   
}