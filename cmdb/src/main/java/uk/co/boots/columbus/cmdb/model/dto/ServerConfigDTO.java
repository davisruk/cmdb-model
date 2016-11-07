/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/dto/EntityDTO.java.e.vm
 */
package uk.co.boots.columbus.cmdb.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Simple DTO for ServerConfig.
 */
public class ServerConfigDTO implements CoreConfigDTO{
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
    public ServerDTO server;

    @JsonIgnore
    public boolean isIdSet() {
        return id != null;
    }
}