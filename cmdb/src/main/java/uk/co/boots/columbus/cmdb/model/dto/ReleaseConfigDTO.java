package uk.co.boots.columbus.cmdb.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}
