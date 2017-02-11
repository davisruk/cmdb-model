package uk.co.boots.columbus.cmdb.model.user.repository;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.user.domain.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
	public Set<Privilege> findByIdNotIn(Collection<Integer> privileges);
}
