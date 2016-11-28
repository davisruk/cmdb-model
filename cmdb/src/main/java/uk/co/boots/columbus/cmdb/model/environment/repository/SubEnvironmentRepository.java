package uk.co.boots.columbus.cmdb.model.environment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;

public interface SubEnvironmentRepository extends JpaRepository<SubEnvironment, Long> {
	public SubEnvironment findOne(Long id);
}
