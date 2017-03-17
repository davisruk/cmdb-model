package uk.co.boots.columbus.cmdb.model.core.dto;

import uk.co.boots.columbus.cmdb.model.core.domain.Lockable;

public interface LockableDTO extends Lockable{
	public boolean isIdSet();
}
