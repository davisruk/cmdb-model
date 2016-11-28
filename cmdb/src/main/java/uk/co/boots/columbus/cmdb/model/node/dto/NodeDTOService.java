package uk.co.boots.columbus.cmdb.model.node.dto;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import uk.co.boots.columbus.cmdb.model.node.domain.Node;

@Service
public class NodeDTOService {

	@Inject 
	private NodeIPDTOService nodeIPDTOService;
	
	@Inject 
	private NodeRelationshipDTOService nodeRelationshipDTOService;
	
	public NodeDTO toDTO(Node node) {
		return toDTO(node, 1);
	}

	/**
	 * Converts the passed node to a DTO. The depth is used to
	 * control the amount of association you want. It also prevents potential
	 * infinite serialization cycles.
	 *
	 * @param node
	 * @param depth
	 *            the depth of the serialization. A depth equals to 0, means no
	 *            x-to-one association will be serialized. A depth equals to 1
	 *            means that xToOne associations will be serialized. 2 means,
	 *            xToOne associations of xToOne associations will be serialized,
	 *            etc.
	 */
	public NodeDTO toDTO(Node node, int depth) {
		if (node == null) {
			return null;
		}

		NodeDTO dto = new NodeDTO();

		dto.id = node.getId();
		dto.nodeType = node.getNodeType();

		if (depth-- > 0) {
			dto.relationships = nodeRelationshipDTOService.toDTO(node.getRelationships(), depth);
			dto.nodeIPs = nodeIPDTOService.toDTO(node.getNodeIPs(), depth);
		}

		return dto;
	}

	/**
	 * Converts the passed dto to a PackageInfo. Convenient for query by
	 * example.
	 */
	public Node toEntity(NodeDTO dto) {
		return toEntity(dto, 1);
	}

	/**
	 * Converts the passed dto to a PackageInfo. Convenient for query by
	 * example.
	 */
	public Node toEntity(NodeDTO dto, int depth) {
		if (dto == null) {
			return null;
		}

		Node node = new Node();

		node.setId(dto.id);
		node.setNodeType(dto.nodeType);

		if (depth-- > 0) {
			node.setRelationships(nodeRelationshipDTOService.toEntity(dto.relationships, depth));
			node.setNodeIPs(nodeIPDTOService.toEntity(dto.nodeIPs, depth));
		}

		return node;
	}

}
