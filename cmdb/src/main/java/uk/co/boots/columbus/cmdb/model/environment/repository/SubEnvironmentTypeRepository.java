package uk.co.boots.columbus.cmdb.model.environment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironmentType;

public interface SubEnvironmentTypeRepository extends JpaRepository<SubEnvironmentType, Long> {

	List<SubEnvironmentType> findByIdNotIn(List<Long>ids); 
}
