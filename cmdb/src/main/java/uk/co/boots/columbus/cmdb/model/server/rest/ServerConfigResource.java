package uk.co.boots.columbus.cmdb.model.server.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.core.rest.support.AutoCompleteQuery;
import uk.co.boots.columbus.cmdb.model.core.rest.support.CopyContainer;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerConfigDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerConfigDTOService;
import uk.co.boots.columbus.cmdb.model.server.repository.ServerConfigRepository;

@RestController
@RequestMapping("/api/serverConfigs")
public class ServerConfigResource {

    private final Logger log = LoggerFactory.getLogger(ServerConfigResource.class);

    @Inject
    private ServerConfigRepository serverConfigRepository;
    @Inject
    private ServerConfigDTOService serverConfigDTOService;

    /**
     * Create a new ServerConfig.
     */
    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ServerConfigDTO> create(@RequestBody ServerConfigDTO serverConfigDTO) throws URISyntaxException {

        log.debug("Create ServerConfigDTO : {}", serverConfigDTO);

        if (serverConfigDTO.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create ServerConfig with existing ID").body(null);
        }

        ServerConfigDTO result = serverConfigDTOService.save(serverConfigDTO);

        return ResponseEntity.created(new URI("/api/serverConfigs/" + result.id)).body(result);
    }

    /**
    * Find by id ServerConfig.
    */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ServerConfigDTO> findById(@PathVariable Long id) throws URISyntaxException {

        log.debug("Find by id ServerConfig : {}", id);

        return Optional.ofNullable(serverConfigDTOService.findOne(id)).map(serverConfigDTO -> new ResponseEntity<>(serverConfigDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update ServerConfig.
     */
    @RequestMapping(value = "/", method = PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ServerConfigDTO> update(@RequestBody ServerConfigDTO serverConfigDTO) throws URISyntaxException {

        log.debug("Update ServerConfigDTO : {}", serverConfigDTO);

        if (!serverConfigDTO.isIdSet()) {
            return create(serverConfigDTO);
        }

        ServerConfigDTO result = serverConfigDTOService.save(serverConfigDTO);

        return ResponseEntity.ok().body(result);
    }

    /**
     * Find a Page of ServerConfig using query by example.
     */
    @RequestMapping(value = "/page", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<ServerConfigDTO>> findAll(@RequestBody PageRequestByExample<ServerConfigDTO> prbe) throws URISyntaxException {
        PageResponse<ServerConfigDTO> pageResponse = serverConfigDTOService.findAll(prbe);
        return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
    }

    /**
    * Auto complete support.
    */
    @RequestMapping(value = "/complete", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServerConfigDTO>> complete(@RequestBody AutoCompleteQuery acq) throws URISyntaxException {

        List<ServerConfigDTO> results = serverConfigDTOService.complete(acq.query, acq.maxResults);

        return new ResponseEntity<>(results, new HttpHeaders(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/copy", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> copyRelease(@RequestBody CopyContainer configIds) throws URISyntaxException {
    	serverConfigDTOService.copyConfigForServer(configIds.getFromId(), configIds.getToId());
    	return ResponseEntity.ok().build();
    }
    

    /**
     * Delete by id ${}entity.model.type}.
     */
    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) throws URISyntaxException {

        log.debug("Delete by id ServerConfig : {}", id);

        try {
            serverConfigRepository.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely org.hibernate.exception.ConstraintViolationException
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}