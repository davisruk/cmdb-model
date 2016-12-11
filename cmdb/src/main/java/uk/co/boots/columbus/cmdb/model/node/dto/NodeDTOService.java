package uk.co.boots.columbus.cmdb.model.node.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import uk.co.boots.columbus.cmdb.model.node.domain.Node;
import uk.co.boots.columbus.cmdb.model.node.repository.NodeRepository;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTOService;
import uk.co.boots.columbus.cmdb.model.server.repository.ServerRepository;

@Service
public class NodeDTOService {

//	@Inject 
//	private VIPDTOService vipDTOService;
	@Inject 
	private NodeRelationshipDTOService nodeRelationshipDTOService;
	@Inject 
	private NodeRepository nodeRepository;
	@Inject 
	private ServerDTOService serverDTOService;
	@Inject 
	private ServerRepository serverRepo;
	
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

		if (depth-- > 0) {
			dto.relationships = nodeRelationshipDTOService.toDTO(node.getRelationships(), depth);
			//dto.nodeIPs = nodeIPDTOService.toDTO(node.getNodeIPs(), depth);
			//dto.vips = vipDTOService.toDTO(node.getVIPs(), depth);
		}

		return dto;
	}

	/**
	 * Converts the passed dto to a PackageInfo. Convenient for query by
	 * example.
	 */
/*	public Node toEntity(NodeDTO dto) {
		return toEntity(dto, 1);
	}
*/
	/**
	 * Converts the passed dto to a PackageInfo. Convenient for query by
	 * example.
	 */
	public Node toEntity(NodeDTO dto, int depth, Node node) {
		if (dto == null) {
			return null;
		}

		node.setId(dto.id);


		if (depth-- > 0) {
			node.setRelationships(nodeRelationshipDTOService.toEntity(dto.relationships, depth));
			//node.setNodeIPs(nodeIPDTOService.toEntity(dto.nodeIPs, depth));
			//node.setVIPs(vipDTOService.toEntity(dto.vips, depth));
		}

		return node;
	}
	public List<Node> toEntity(List<NodeDTO> dtoList, int depth) {
		if (dtoList == null)
			return null;
		List<Node> ret = new ArrayList<Node>();
		for (NodeDTO dto : dtoList)
			ret.add(toEntity(dto, depth,  new Server())); // FIX THIS - it needs to be generic!
		return ret;
	}

	public List<NodeDTO> toDTO(List<Node> nodeList, int depth) {
		if (nodeList == null)
			return null;
		List<NodeDTO> ret = new ArrayList<NodeDTO>();
		for (Node n : nodeList)
			ret.add(toDTO(n, depth));
		return ret;
	}
	
	public List<ServerDTO> getServerDTOList(Set<? extends Node> nodeList, int depth) {
		if (nodeList == null)
			return null;
		List<ServerDTO> ret = new ArrayList<ServerDTO>();
		for (Node s : nodeList)
			if (s instanceof Server)
				ret.add(serverDTOService.toDTO((Server)s, depth));
		return ret;
	}

}
