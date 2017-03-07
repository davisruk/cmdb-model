package uk.co.boots.columbus.cmdb.model.core.domain;

import uk.co.boots.columbus.cmdb.model.core.dto.LockableDTO;

public interface LockStrategy {
	public void lockEntity(LockableEntity entity, LockableDTO dto);
}
