package uk.co.boots.columbus.cmdb.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReleaseConfigDTO {
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
