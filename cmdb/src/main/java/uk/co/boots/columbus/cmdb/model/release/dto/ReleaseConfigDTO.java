package uk.co.boots.columbus.cmdb.model.release.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.globalconfig.dto.GlobalconfigDTO;
import uk.co.boots.columbus.cmdb.model.hiera.dto.CoreConfigDTO;

public class ReleaseConfigDTO implements CoreConfigDTO{

	public Long id;
    public String parameter;
    public String value;
    public String hieraAddress;
    public ReleaseDTO release;
    public Boolean recursiveByEnv;
    public Boolean recursiveBySubEnv;
    public String notes;
    public Boolean sensitive;
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
    public ReleaseConfigDTO getCopy() {
        ReleaseConfigDTO copy = new ReleaseConfigDTO();
        copy.id = id;
        copy.parameter = parameter;
        copy.value = value;
        copy.hieraAddress = hieraAddress;
        copy.recursiveByEnv = recursiveByEnv;
        copy.recursiveBySubEnv = recursiveBySubEnv;
        copy.notes = notes;
        copy.sensitive = sensitive;
        copy.arrayItem = arrayItem;
        copy.release = release;
        return copy;

    }
	
}
