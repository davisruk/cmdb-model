package uk.co.boots.columbus.cmdb.model.core.rest.support;

public class CopyContainer {
	private Long fromId;
	private Long toId;
	
	public CopyContainer() {
		super();
	}

	public CopyContainer(Long fromId, Long toId) {
		super();
		this.fromId = fromId;
		this.toId = toId;
	}
	public Long getFromId() {
		return fromId;
	}
	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}
	public Long getToId() {
		return toId;
	}
	public void setToId(Long toId) {
		this.toId = toId;
	}
	
}
