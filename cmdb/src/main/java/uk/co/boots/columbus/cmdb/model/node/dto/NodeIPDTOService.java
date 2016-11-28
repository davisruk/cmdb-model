package uk.co.boots.columbus.cmdb.model.node.dto;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.node.domain.Node;
import uk.co.boots.columbus.cmdb.model.node.domain.NodeIP;
import uk.co.boots.columbus.cmdb.model.node.repository.NodeIPRepository;
import uk.co.boots.columbus.cmdb.model.node.repository.NodeRepository;

@Service
public class NodeIPDTOService {
	@Inject
	private NodeIPRepository nodeIPRepo;
	@Inject
	private NodeRepository nodeRepo;
	@Inject
	private NodeDTOService nodeDTOService;

	@Transactional
	public NodeIPDTO save(NodeIPDTO dto) {
		if (dto == null) {
			return null;
		}

		NodeIP nodeIP;
		if (dto.isIdSet()) {
			nodeIP = nodeIPRepo.findOne(dto.id);
		} else {
			nodeIP = new NodeIP();
		}

		nodeIP.setIpAddress(dto.ipAddress);
		nodeIP.setIPType(dto.ipType);

		if (dto.node == null) {
			nodeIP.setNode(null);
		} else {
			Node node = nodeIP.getNode();
			if (node == null || (node.getId().compareTo(dto.node.id) != 0)) {
				nodeIP.setNode(nodeRepo.findOne(dto.node.id));
			}
		}

		return toDTO(nodeIPRepo.save(nodeIP));
	}

	public NodeIPDTO toDTO(NodeIP nodeIP) {
		return toDTO(nodeIP, 1);
	}

	/**
	 * Converts the passed nodeIP to a DTO. The depth is used to
	 * control the amount of association you want. It also prevents potential
	 * infinite serialization cycles.
	 *
	 * @param nodeIP
	 * @param depth
	 *            the depth of the serialization. A depth equals to 0, means no
	 *            x-to-one association will be serialized. A depth equals to 1
	 *            means that xToOne associations will be serialized. 2 means,
	 *            xToOne associations of xToOne associations will be serialized,
	 *            etc.
	 */
	public NodeIPDTO toDTO(NodeIP nodeIP, int depth) {
		if (nodeIP == null) {
			return null;
		}

		NodeIPDTO dto = new NodeIPDTO();

		dto.id = nodeIP.getId();
		dto.ipAddress = nodeIP.getIpAddress();
		dto.ipType = nodeIP.getIPType();

			nodeIP.setNode(null);

		
		if (depth-- > 0) {
			dto.node = nodeDTOService.toDTO(nodeIP.getNode(), depth);
		}

		return dto;
	}

	/**
	 * Converts the passed dto to a PackageInfo. Convenient for query by
	 * example.
	 */
	public NodeIP toEntity(NodeIPDTO dto) {
		return toEntity(dto, 1);
	}

	/**
	 * Converts the passed dto to a PackageInfo. Convenient for query by
	 * example.
	 */
	public NodeIP toEntity(NodeIPDTO dto, int depth) {
		if (dto == null) {
			return null;
		}

		NodeIP nodeIP = new NodeIP();

		nodeIP.setId(dto.id);
		nodeIP.setIpAddress(dto.ipAddress);
		nodeIP.setIPType(dto.ipType);

		if (depth-- > 0) {
			nodeIP.setNode(nodeDTOService.toEntity(dto.node, depth));
		}

		return nodeIP;
	}
	
	public List<NodeIP> toEntity(List<NodeIPDTO> dtoList, int depth) {
		if (dtoList == null)
			return null;
		List<NodeIP> ret = new ArrayList<NodeIP>();
		for (NodeIPDTO dto : dtoList)
			ret.add(toEntity(dto, depth));
		return ret;
	}

	public List<NodeIPDTO> toDTO(List<NodeIP> nodeIPList, int depth) {
		if (nodeIPList == null)
			return null;
		List<NodeIPDTO> ret = new ArrayList<NodeIPDTO>();
		for (NodeIP n : nodeIPList)
			ret.add(toDTO(n, depth));
		return ret;
	}
	
}
