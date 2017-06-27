package uk.co.boots.columbus.cmdb.model.hiera.dto;

public interface CoreConfigDTO {
	public Long getId();

	public String getParameter();

	public String getValue();

	public String getHieraAddress();
	
	public void setParameter(String param);

	public void setValue(String val);

	public void setHieraAddress(String addr);
	
	public default Boolean isArrayItem() {
		return false;
	}
}
