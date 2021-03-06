/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/dto/EntityDTO.java.e.vm
 */
package uk.co.boots.columbus.cmdb.model.release.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Simple DTO for ReleaseDataType.
 */
public class ReleaseDataTypeDTO {
    public Long id;
    public String name;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}