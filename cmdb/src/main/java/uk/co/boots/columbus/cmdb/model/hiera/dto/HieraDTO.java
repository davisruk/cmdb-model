package uk.co.boots.columbus.cmdb.model.hiera.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;

public class HieraDTO {
	// added JsonRawValue to stop ObjectMapper creating escape sequence in string e.g. stop \= rendering as \\= 
	@JsonRawValue
	public String value;
	@JsonRawValue
	public String address;
	@JsonIgnore
	public Boolean renderAsArray;
	
	public HieraDTO(String value, String address, Boolean renderAsArray) {
		super();
		this.value = value;
		this.address = address;
		this.renderAsArray = renderAsArray;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HieraDTO other = (HieraDTO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HieraDTO [value=" + value + ", address=" + address + ", renderAsArray=" + renderAsArray + "]";
	}

	
}
