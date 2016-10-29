package uk.co.boots.columbus.cmdb.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.domain.Environment;
import uk.co.boots.columbus.cmdb.model.domain.Release;
import uk.co.boots.columbus.cmdb.model.domain.Role;
import uk.co.boots.columbus.cmdb.model.domain.User;
import uk.co.boots.columbus.cmdb.model.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.repository.RoleRepository;
import uk.co.boots.columbus.cmdb.model.repository.UserRepository;

@Service
public class UserDTOService {
    @Inject
    private UserRepository userRepository;
    @Inject
    private RoleDTOService roleDTOService;
    @Inject
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public UserDTO findOne(Integer id) {
        return toDTO(userRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<UserDTO> complete(String query, int maxResults) {
        List<User> results = userRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<UserDTO> findAll(PageRequestByExample<UserDTO> req) {
        Example<User> example = null;
        User user = toEntity(req.example);

        if (user != null) {
            example = Example.of(user);
        }

        Page<User> page;
        if (example != null) {
            page = userRepository.findAll(example, req.toPageable());
        } else {
            page = userRepository.findAll(req.toPageable());
        }

        List<UserDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public UserDTO save(UserDTO dto) {
    	return null;
    }
    

    /**
     * Converts the passed environment to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param environment
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    
    public UserDTO toDTO(User user) {
    	return toDTO(user,1);
    }
    
    public UserDTO toDTO(User user, int depth) {
        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();

        dto.id = user.getId();
        dto.userName = user.getUserName();
        dto.email = user.getEmail();
        dto.password = user.getPassword();
        dto.enabled = user.getEnabled();
        if (depth-- > 0) {
            dto.roles = roleDTOService.toDTO(user.getRoles(), depth);
        }

        return dto;
    }
	public List<UserDTO> toDTO(List<User> users, int depth) {
        if (users == null) {
            return null;
        }

        List<UserDTO> dtoList = new ArrayList<UserDTO>();

        for (User user : users ){
        	dtoList.add(toDTO(user, depth));
        }
        return dtoList;
    }

    public User toEntity(UserDTO userDTO) {
    	return toEntity(userDTO,1);
    }

	public User toEntity(UserDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        User user = new User();

        user.setId(dto.id);
        user.setUserName(dto.userName);
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        user.setEnabled(dto.enabled);
        
        if (depth-- > 0) {
            user.setRoles(roleDTOService.toEntity(dto.roles, depth));
        }

        return user;
    }

	public List<User> toEntity(List<UserDTO> users, int depth) {
        if (users == null) {
            return null;
        }

        List<User> userList = new ArrayList<User>();

        for (UserDTO userDTO : users ){
        	userList.add(toEntity(userDTO, depth));
        }
        return userList;
    }

}
