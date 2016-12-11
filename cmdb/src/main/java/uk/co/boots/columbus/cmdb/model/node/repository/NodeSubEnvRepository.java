package uk.co.boots.columbus.cmdb.model.node.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.node.domain.Node;
import uk.co.boots.columbus.cmdb.model.node.domain.NodeSubEnvironment;

public interface NodeSubEnvRepository extends JpaRepository<NodeSubEnvironment, Long> {

	public Node findOne(long id);
}
