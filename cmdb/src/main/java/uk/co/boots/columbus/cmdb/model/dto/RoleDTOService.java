package uk.co.boots.columbus.cmdb.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.domain.Role;
import uk.co.boots.columbus.cmdb.model.domain.User;
import uk.co.boots.columbus.cmdb.model.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.repository.RoleRepository;
import uk.co.boots.columbus.cmdb.model.repository.UserRepository;

@Service
public class RoleDTOService {

    @Inject
    private RoleRepository roleRepository;
    @Inject
    private UserDTOService userDTOService;
    @Inject
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public RoleDTO findOne(Integer id) {
        return toDTO(roleRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> complete(String query, int maxResults) {
        List<Role> results = roleRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<RoleDTO> findAll(PageRequestByExample<RoleDTO> req) {
        Example<Role> example = null;
        Role role = toEntity(req.example);

        if (role != null) {
            example = Example.of(role);
        }

        Page<Role> page;
        if (example != null) {
            page = roleRepository.findAll(example, req.toPageable());
        } else {
            page = roleRepository.findAll(req.toPageable());
        }

        List<RoleDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public RoleDTO save(RoleDTO dto) {
        if (dto == null) {
            return null;
        }

        Role role;
        if (dto.isIdSet()) {
            role = roleRepository.findOne(dto.id);
        } else {
            role = new Role();
        }

        role.setName(dto.name);

        if (dto.users == null) {
            role.setUsers(null);
        } else {
            List<User> users = role.getUsers();
            for (UserDTO dtoUser: dto.users){
            	User entityUser = extractEntityUser (users, dtoUser);
            	if (entityUser == null){
            		role.addUser(userDTOService.toEntity(dtoUser));
            		if (dtoUser.roles == null)
            			dtoUser.roles = new ArrayList<RoleDTO>();
            		dtoUser.roles.add(dto);
            	}
            }
        }

        return toDTO(roleRepository.save(role));
    	

    }
    
    private  User extractEntityUser (List<User> users, UserDTO dtoUser){
        if (users == null || users.size() == 0)
        	return null;
    	for (User user: users){
        	if (dtoUser.id == user.getId())
        		return user;
        }
        return null;
    }
    
    public RoleDTO toDTO(Role role) {
    	return toDTO (role, 1);
    }

    public RoleDTO toDTO(Role role, int depth) {
        if (role == null) {
            return null;
        }

        RoleDTO dto = new RoleDTO();

        dto.id = role.getId();
        dto.name = role.getName();
        if (depth-- > 0) {
            dto.users = userDTOService.toDTO(role.getUsers(), depth);
        }

        return dto;
    }
	
	public List<RoleDTO> toDTO(List<Role> roles, int depth) {
        if (roles == null) {
            return null;
        }

        List<RoleDTO> dtoList = new ArrayList<RoleDTO>();

        for (Role role : roles ){
        	dtoList.add(toDTO(role, 1));
        }
        return dtoList;
    }
	
	public Role toEntity(RoleDTO dto) {
		return toEntity(dto, 1);
	}
	
	public Role toEntity(RoleDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        Role role = new Role();

        role.setId(dto.id);
        role.setName(dto.name);

        if (depth-- > 0) {
            role.setUsers(userDTOService.toEntity(dto.users, depth));
        }

        return role;
    }

	public List<Role> toEntity(List<RoleDTO> roles, int depth) {
        if (roles == null) {
            return null;
        }

        List<Role> roleList = new ArrayList<Role>();

        for (RoleDTO roleDTO : roles ){
        	roleList.add(toEntity(roleDTO, depth));
        }
        return roleList;
    }
	
}
