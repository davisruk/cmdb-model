package uk.co.boots.columbus.cmdb.model.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseDTO implements LockableDTO{

	public Long id;
    public Long version;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }

}
