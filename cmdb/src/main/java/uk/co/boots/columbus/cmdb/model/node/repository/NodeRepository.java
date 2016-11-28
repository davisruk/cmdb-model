package uk.co.boots.columbus.cmdb.model.node.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.co.boots.columbus.cmdb.model.node.domain.Node;

public interface NodeRepository extends JpaRepository<Node, Long> {

	public Node findOne(long id);
	
}
