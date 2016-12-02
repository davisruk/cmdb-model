package uk.co.boots.columbus.cmdb.model.node.dto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.node.domain.Node;
import uk.co.boots.columbus.cmdb.model.node.domain.NodeRelationship;
import uk.co.boots.columbus.cmdb.model.node.repository.NodeRelationshipRepository;
import uk.co.boots.columbus.cmdb.model.node.repository.NodeRepository;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;

@Service
public class NodeRelationshipDTOService {

	@Inject
	private NodeRelationshipRepository nodeRelRepo;
	@Inject
	private NodeRepository nodeRepo;
	@Inject
	private NodeDTOService nodeDTOService;

	@Transactional
	public NodeRelationshipDTO save(NodeRelationshipDTO dto) {
		if (dto == null) {
			return null;
		}

		NodeRelationship nodeRelationship;
		if (dto.isIdSet()) {
			nodeRelationship = nodeRelRepo.findOne(dto.id);
		} else {
			nodeRelationship = new NodeRelationship();
		}

		nodeRelationship.setEndPort(dto.endPort);
		nodeRelationship.setStartPort(dto.startPort);
		nodeRelationship.setProtocol(dto.protocol);

		if (dto.publishingNode == null) {
			nodeRelationship.setPublishingNode(null);
		} else {
			Node pubNode = nodeRelationship.getPublishingNode();
			if (pubNode == null || (pubNode.getId().compareTo(dto.publishingNode.id) != 0)) {
				nodeRelationship.setPublishingNode(nodeRepo.findOne(dto.publishingNode.id));
			}
		}

		if (dto.consumingNode == null) {
			nodeRelationship.setConsumingNode(null);
		} else {
			Node conNode = nodeRelationship.getConsumingNode();
			if (conNode == null || (conNode.getId().compareTo(dto.consumingNode.id) != 0)) {
				nodeRelationship.setConsumingNode(nodeRepo.findOne(dto.consumingNode.id));
			}
		}

		return toDTO(nodeRelRepo.save(nodeRelationship));
	}

	public NodeRelationshipDTO toDTO(NodeRelationship packageInfo) {
		return toDTO(packageInfo, 1);
	}

	/**
	 * Converts the passed nodeRelationship to a DTO. The depth is used to
	 * control the amount of association you want. It also prevents potential
	 * infinite serialization cycles.
	 *
	 * @param nodeRelationship
	 * @param depth
	 *            the depth of the serialization. A depth equals to 0, means no
	 *            x-to-one association will be serialized. A depth equals to 1
	 *            means that xToOne associations will be serialized. 2 means,
	 *            xToOne associations of xToOne associations will be serialized,
	 *            etc.
	 */
	public NodeRelationshipDTO toDTO(NodeRelationship nodeRelationship, int depth) {
		if (nodeRelationship == null) {
			return null;
		}

		NodeRelationshipDTO dto = new NodeRelationshipDTO();

		dto.id = nodeRelationship.getId();
		dto.endPort = nodeRelationship.getEndPort();
		dto.protocol = nodeRelationship.getProtocol();
		dto.startPort = nodeRelationship.getEndPort();

		if (depth-- > 0) {
			dto.publishingNode = nodeDTOService.toDTO(nodeRelationship.getPublishingNode(), depth);
			dto.consumingNode = nodeDTOService.toDTO(nodeRelationship.getConsumingNode(), depth);
		}

		return dto;
	}

	/**
	 * Converts the passed dto to a PackageInfo. Convenient for query by
	 * example.
	 */
	public NodeRelationship toEntity(NodeRelationshipDTO dto) {
		return toEntity(dto, 1);
	}

	/**
	 * Converts the passed dto to a PackageInfo. Convenient for query by
	 * example.
	 */
	public NodeRelationship toEntity(NodeRelationshipDTO dto, int depth) {
		if (dto == null) {
			return null;
		}

		NodeRelationship nodeRelationship = new NodeRelationship();

		nodeRelationship.setId(dto.id);
		nodeRelationship.setEndPort(dto.endPort);
		nodeRelationship.setProtocol(dto.protocol);
		nodeRelationship.setEndPort(dto.startPort);

		if (depth-- > 0) {
			nodeRelationship.setPublishingNode(nodeDTOService.toEntity(dto.publishingNode, depth, new Server())); // MUST change new Server
			nodeRelationship.setConsumingNode(nodeDTOService.toEntity(dto.consumingNode, depth, new Server()));// to actual NODE type
		}

		return nodeRelationship;
	}
	public List<NodeRelationship> toEntity(List<NodeRelationshipDTO> dtoList, int depth) {
		if (dtoList == null)
			return null;
		List<NodeRelationship> ret = new ArrayList<NodeRelationship>();
		for (NodeRelationshipDTO dto : dtoList)
			ret.add(toEntity(dto, depth));
		return ret;
	}

	public List<NodeRelationshipDTO> toDTO(List<NodeRelationship> nodeRelList, int depth) {
		if (nodeRelList == null)
			return null;
		List<NodeRelationshipDTO> ret = new ArrayList<NodeRelationshipDTO>();
		for (NodeRelationship n : nodeRelList)
			ret.add(toDTO(n, depth));
		return ret;
	}
	
}
