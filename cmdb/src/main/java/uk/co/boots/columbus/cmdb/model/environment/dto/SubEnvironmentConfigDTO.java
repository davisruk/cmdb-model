package uk.co.boots.columbus.cmdb.model.environment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.hiera.dto.CoreConfigDTO;

public class SubEnvironmentConfigDTO implements CoreConfigDTO{
    public Long id;
	public String parameter;
    public String value;
    public String hieraAddress;
    public String notes;
    public Boolean sensitive;
    public SubEnvironmentDTO subEnvironment;
    public Boolean arrayItem;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getHieraAddress() {
		return hieraAddress;
	}

	public void setHieraAddress(String hieraAddress) {
		this.hieraAddress = hieraAddress;
	}

	public SubEnvironmentDTO getSubEnvironment() {
		return subEnvironment;
	}

	public void setSubEnvironment(SubEnvironmentDTO subEnvironment) {
		this.subEnvironment = subEnvironment;
	}
	
	public Boolean isArrayItem(){
		return arrayItem;
	}
	

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}
