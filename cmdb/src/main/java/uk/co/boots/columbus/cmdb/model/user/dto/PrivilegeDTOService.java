package uk.co.boots.columbus.cmdb.model.user.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import uk.co.boots.columbus.cmdb.model.user.domain.Privilege;
import uk.co.boots.columbus.cmdb.model.user.domain.Role;
import uk.co.boots.columbus.cmdb.model.user.repository.PrivilegeRepository;
@Service
public class PrivilegeDTOService {

	@Inject
	private PrivilegeRepository privRepo;
	@Inject
	private RoleDTOService roleService;
	
	public List<PrivilegeDTO> getUnassignedPrivilegesForRole(Integer id){
		Role r = roleService.toEntity(roleService.findOne(id)); 
		if (r.getPrivileges().size() == 0)
			return getAllPrivileges();
		List<Integer> ids = new ArrayList<Integer>();
		for (Privilege p:r.getPrivileges())
			ids.add(p.getId());
		return toDTO(privRepo.findByIdNotIn(ids), 1);
	}

	public List<PrivilegeDTO> getAllPrivileges(){
		return toDTO(privRepo.findAll(), 1);
	}

	public PrivilegeDTO toDTO(Privilege priv) {
    	return toDTO (priv, 1);
    }

    public PrivilegeDTO toDTO(Privilege priv, int depth) {
        if (priv == null) {
            return null;
        }

        PrivilegeDTO dto = new PrivilegeDTO();

        dto.id = priv.getId();
        dto.name = priv.getName();
        if (depth-- > 0) {
            dto.roles = roleService.toDTO(priv.getRoles(), depth);
        }

        return dto;
    }
	
	public List<PrivilegeDTO> toDTO(Collection<Privilege> roles, int depth) {
        if (roles == null) {
            return null;
        }

        List<PrivilegeDTO> dtoList = new ArrayList<PrivilegeDTO>();

        for (Privilege role : roles ){
        	dtoList.add(toDTO(role, depth));
        }
        return dtoList;
    }

	public Privilege toEntity(PrivilegeDTO dto) {
		return toEntity(dto, 1);
	}
	
	public Privilege toEntity(PrivilegeDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        Privilege priv;
        if (dto.isIdSet()) {
            priv = privRepo.findOne(dto.id);
        } else {
            priv = new Privilege();
        }
        priv.setId(dto.id);
        priv.setName(dto.name);

        if (depth-- > 0) {
            priv.setRoles(roleService.toEntity(dto.roles, depth));
        }

        return priv;
    }

	public Set<Privilege> toEntity(List<PrivilegeDTO> privs, int depth) {
        if (privs == null) {
            return null;
        }

        Set<Privilege> privSet = new HashSet<Privilege>();

        for (PrivilegeDTO privDTO : privs ){
        	privSet.add(toEntity(privDTO, depth));
        }
        return privSet;
    }
	
}
