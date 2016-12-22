package uk.co.boots.columbus.cmdb.model.release.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.co.boots.columbus.cmdb.model.hiera.dto.CoreConfigDTO;

public class ReleaseConfigDTO implements CoreConfigDTO{
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
    public ReleaseDTO release;
    public Boolean recursiveByEnv;
    public Boolean recursiveBySubEnv;
    public String notes;
    public Boolean sensitive;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}
