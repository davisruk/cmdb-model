package uk.co.boots.columbus.cmdb.model.component.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.core.dto.LockableDTO;
import uk.co.boots.columbus.cmdb.model.hiera.dto.CoreConfigDTO;

/**
 * Simple DTO for ComponentConfig.
 */
public class ComponentConfigDTO implements CoreConfigDTO, LockableDTO{
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

    public Long id;
    public String parameter;
    public String value;
    public String hieraAddress;
    public String notes;
    public Boolean sensitive;
    public Long version;
    
    public SolutionComponentDTO my_component;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }

	@Override
	public Long getVersion() {
		// TODO Auto-generated method stub
		return version;
	}
}