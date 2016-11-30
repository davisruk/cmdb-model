package uk.co.boots.columbus.cmdb.model.node.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;
import uk.co.boots.columbus.cmdb.model.node.domain.Node;
import uk.co.boots.columbus.cmdb.model.node.domain.NodeRelationship;
import uk.co.boots.columbus.cmdb.model.node.repository.NodeRepository;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerDTOService;
import uk.co.boots.columbus.cmdb.model.server.repository.ServerRepository;

@Service
public class NodeDTOService {

	@Inject 
	private NodeIPDTOService nodeIPDTOService;
	@Inject 
	private NodeRelationshipDTOService nodeRelationshipDTOService;
	@Inject 
	private NodeRepository nodeRepository;
	@Inject 
	private ServerDTOService serverDTOService;
	@Inject 
	private ServerRepository serverRepo;
	
	@Transactional
	public NodeDTO save(NodeDTO dto) {
		boolean inserting = false;

		if (dto == null) {
			return null;
		}

		Node node;
		if (dto.isIdSet()) {
			node = nodeRepository.findOne(dto.id);
		} else {
			node = new Node();
		}

		node.setNodeType(dto.nodeType);

		if (dto.relationships == null) {
			node.setRelationships(null);
		} else {
			List<NodeRelationship> relationships = node.getRelationships();
			// compare relationships with dto relationships and determine what needs persisting 
		}
	
		if (dto.subEnvironments == null) {
			node.setSubEnvironments(null);
		} else {
			List<SubEnvironment> subEnvironments = node.getSubEnvironments();
			// compare subEnvironments with dto subEnvironments and determine what needs persisting 
		}

		nodeRepository.save(node);
		
		List<Server> servers = node.getServers();
		if (servers == null) {
			servers = new ArrayList<>();
			node.setServers(servers);
			inserting=true;
		}

		// This is slow and clunky but if we are to remain stateless
		// there's no real alternative
		// Add any new servers
		if (dto.servers != null){
			for (ServerDTO sDTO : dto.servers) {
				Optional<Server> optional = servers.stream().filter(x -> x.getId().equals(sDTO.id)).findFirst();
				if (!optional.isPresent()) {
					// Add the server to the environment
					// We need to do this because Server owns the M:M relationship
					// Environment will not persist changes to the join table
					Server s = serverDTOService.toEntity(sDTO, 1);
					s.setNode(node);
					serverRepo.save(s);
				}
			}
		}
		// Remove any old servers
		// Only need to check this if updating
		if (!inserting) {
			for (Iterator<Server> it = servers.iterator(); it.hasNext();) {
				Server s = it.next();
				Optional<ServerDTO> optional = dto.servers.stream().filter(x -> x.id.equals(s.getId())).findFirst();
				if (!optional.isPresent()) {
					// same as above - we need to ensure we persist the M:M relationships
					s.setNode(node);
					serverRepo.save(s);
					it.remove();
				}
			}
		}
		return toDTO(node, 2);
	}	
	
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
