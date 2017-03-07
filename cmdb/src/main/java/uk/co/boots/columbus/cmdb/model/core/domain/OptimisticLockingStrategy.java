package uk.co.boots.columbus.cmdb.model.core.domain;

import javax.persistence.OptimisticLockException;

import uk.co.boots.columbus.cmdb.model.core.dto.LockableDTO;

//@Component
public class OptimisticLockingStrategy implements LockStrategy{

	public void lockEntity(LockableEntity entity, LockableDTO dto) {
		if (!entity.getVersion().equals(dto.getVersion())) {
			throw new OptimisticLockException();
		}
	}
}
