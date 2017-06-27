package uk.co.boots.columbus.cmdb.model.component.dto;

import uk.co.boots.columbus.cmdb.model.core.dto.BaseDTO;
import uk.co.boots.columbus.cmdb.model.hiera.dto.CoreConfigDTO;

/**
 * Simple DTO for ComponentConfig.
 */
public class ComponentConfigDTO extends BaseDTO implements CoreConfigDTO {

    public String parameter;
    public String value;
    public String hieraAddress;
    public String notes;
    public Boolean sensitive;
    public Boolean arrayItem;
    
    public SolutionComponentDTO my_component;

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
	
}