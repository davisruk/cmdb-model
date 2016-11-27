package uk.co.boots.columbus.cmdb.model.user.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.boots.columbus.cmdb.model.core.rest.support.CORSSupport;
import uk.co.boots.columbus.cmdb.model.user.dto.RoleDTO;
import uk.co.boots.columbus.cmdb.model.user.dto.UserDTOService;

@RestController
@RequestMapping("/api/unassignedrolesforuser")

public class UserRoleResource {
	@Inject
	private UserDTOService userDTOService;

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
	
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDTO>> findUnassignedRoles(@PathVariable Integer id, HttpServletRequest request, 
            HttpServletResponse response) throws URISyntaxException {

        log.debug("Find unassigned roles for id User : {}", id);
        List<RoleDTO> results = userDTOService.getRolesNotAssignedToUser(id);

        return new ResponseEntity<>(results, CORSSupport.createCORSHeaders(), HttpStatus.OK);
    }

}
