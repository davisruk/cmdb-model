package uk.co.boots.columbus.cmdb.model.environment.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.boots.columbus.cmdb.model.core.rest.support.CORSSupport;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTOService;

@RestController
@RequestMapping("/api/subenvironments")
public class SubEnvironmentResource {

    private final Logger log = LoggerFactory.getLogger(EnvironmentResource.class);

    @Inject
    private SubEnvironmentDTOService subEnvironmentDTOService;

    /**
     * Create a new SubEnvironment.
     */
    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SubEnvironmentDTO> create(@RequestBody SubEnvironmentDTO subEnvironmentDTO) throws URISyntaxException {

        log.debug("Create SubEnvironmentDTO : {}", subEnvironmentDTO);

        if (subEnvironmentDTO.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create Environment with existing ID").body(null);
        }

        SubEnvironmentDTO result = subEnvironmentDTOService.save(subEnvironmentDTO);

        return ResponseEntity.created(new URI("/api/subenvironments/" + result.id)).body(result);
    }

    /**
    * Find by id Environment.
    */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SubEnvironmentDTO> findById(@PathVariable Long id) throws URISyntaxException {

        log.debug("Find by id Environment : {}", id);

        SubEnvironmentDTO dto = subEnvironmentDTOService.findOne(id,2);
        return Optional.ofNullable(dto).map(subEnvironmentDTO -> new ResponseEntity<>(subEnvironmentDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
