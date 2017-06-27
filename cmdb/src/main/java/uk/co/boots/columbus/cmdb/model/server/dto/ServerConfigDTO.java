package uk.co.boots.columbus.cmdb.model.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.hiera.dto.CoreConfigDTO;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseConfigDTO;

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

	@Override
	public void setParameter(String param) {
		this.parameter = param;
	}

	@Override
	public void setValue(String val) {
		this.value = val;
	}

	@Override
	public void setHieraAddress(String addr) {
		this.hieraAddress = addr;
	}

	@JsonIgnore
    public ServerConfigDTO getCopy() {
        ServerConfigDTO copy = new ServerConfigDTO();
        copy.id = id;
        copy.parameter = parameter;
        copy.value = value;
        copy.hieraAddress = hieraAddress;
        copy.notes = notes;
        copy.sensitive = sensitive;
        copy.arrayItem = arrayItem;
        copy.server = server;
        return copy;

    }	
}