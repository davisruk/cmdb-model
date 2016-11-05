package uk.co.boots.columbus.cmdb.model.rest;

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

import uk.co.boots.columbus.cmdb.model.dto.ReleaseConfigDTO;
import uk.co.boots.columbus.cmdb.model.dto.ReleaseConfigDTOService;
import uk.co.boots.columbus.cmdb.model.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.repository.ReleaseConfigRepository;
import uk.co.boots.columbus.cmdb.model.rest.support.AutoCompleteQuery;

@RestController
@RequestMapping("/api/releaseConfigs")
public class ReleaseConfigResource {

    private final Logger log = LoggerFactory.getLogger(ReleaseConfigResource.class);

    @Inject
    private ReleaseConfigRepository releaseConfigRepository;
    @Inject
    private ReleaseConfigDTOService releaseConfigDTOService;

    /**
     * Create a new ReleaseConfig.
     */
    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReleaseConfigDTO> create(@RequestBody ReleaseConfigDTO releaseConfigDTO) throws URISyntaxException {

        log.debug("Create ReleaseConfigDTO : {}", releaseConfigDTO);

        if (releaseConfigDTO.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create ReleaseConfig with existing ID").body(null);
        }

        ReleaseConfigDTO result = releaseConfigDTOService.save(releaseConfigDTO);

        return ResponseEntity.created(new URI("/api/releaseConfigs/" + result.id)).body(result);
    }

    /**
    * Find by id ReleaseConfig.
    */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReleaseConfigDTO> findById(@PathVariable Long id) throws URISyntaxException {

        log.debug("Find by id ReleaseConfig : {}", id);

        return Optional.ofNullable(releaseConfigDTOService.findOne(id))
                .map(releaseConfigDTO -> new ResponseEntity<>(releaseConfigDTO, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update ReleaseConfig.
     */
    @RequestMapping(value = "/", method = PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReleaseConfigDTO> update(@RequestBody ReleaseConfigDTO releaseConfigDTO) throws URISyntaxException {

        log.debug("Update ReleaseConfigDTO : {}", releaseConfigDTO);

        if (!releaseConfigDTO.isIdSet()) {
            return create(releaseConfigDTO);
        }

        ReleaseConfigDTO result = releaseConfigDTOService.save(releaseConfigDTO);

        return ResponseEntity.ok().body(result);
    }

    /**
     * Find a Page of ReleaseConfig using query by example.
     */
    @RequestMapping(value = "/page", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<ReleaseConfigDTO>> findAll(@RequestBody PageRequestByExample<ReleaseConfigDTO> prbe) throws URISyntaxException {
        PageResponse<ReleaseConfigDTO> pageResponse = releaseConfigDTOService.findAll(prbe);
        return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
    }

    /**
    * Auto complete support.
    */
    @RequestMapping(value = "/complete", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReleaseConfigDTO>> complete(@RequestBody AutoCompleteQuery acq) throws URISyntaxException {

        List<ReleaseConfigDTO> results = releaseConfigDTOService.complete(acq.query, acq.maxResults);

        return new ResponseEntity<>(results, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete by id ${}entity.model.type}.
     */
    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) throws URISyntaxException {

        log.debug("Delete by id ReleaseConfig : {}", id);

        try {
            releaseConfigRepository.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely org.hibernate.exception.ConstraintViolationException
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}