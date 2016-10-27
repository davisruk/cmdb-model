/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/rest/EntityResource.java.e.vm
 */
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

import uk.co.boots.columbus.cmdb.model.dto.ReleaseDataTypeDTO;
import uk.co.boots.columbus.cmdb.model.dto.ReleaseDataTypeDTOService;
import uk.co.boots.columbus.cmdb.model.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.repository.ReleaseDataTypeRepository;
import uk.co.boots.columbus.cmdb.model.rest.support.AutoCompleteQuery;

@RestController
@RequestMapping("/api/releaseDataTypes")
public class ReleaseDataTypeResource {

    private final Logger log = LoggerFactory.getLogger(ReleaseDataTypeResource.class);

    @Inject
    private ReleaseDataTypeRepository releaseDataTypeRepository;
    @Inject
    private ReleaseDataTypeDTOService releaseDataTypeDTOService;

    /**
     * Create a new ReleaseDataType.
     */
    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReleaseDataTypeDTO> create(@RequestBody ReleaseDataTypeDTO releaseDataTypeDTO) throws URISyntaxException {

        log.debug("Create ReleaseDataTypeDTO : {}", releaseDataTypeDTO);

        if (releaseDataTypeDTO.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create ReleaseDataType with existing ID").body(null);
        }

        ReleaseDataTypeDTO result = releaseDataTypeDTOService.save(releaseDataTypeDTO);

        return ResponseEntity.created(new URI("/api/releaseDataTypes/" + result.id)).body(result);
    }

    /**
    * Find by id ReleaseDataType.
    */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReleaseDataTypeDTO> findById(@PathVariable Long id) throws URISyntaxException {

        log.debug("Find by id ReleaseDataType : {}", id);

        return Optional.ofNullable(releaseDataTypeDTOService.findOne(id)).map(releaseDataTypeDTO -> new ResponseEntity<>(releaseDataTypeDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update ReleaseDataType.
     */
    @RequestMapping(value = "/", method = PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ReleaseDataTypeDTO> update(@RequestBody ReleaseDataTypeDTO releaseDataTypeDTO) throws URISyntaxException {

        log.debug("Update ReleaseDataTypeDTO : {}", releaseDataTypeDTO);

        if (!releaseDataTypeDTO.isIdSet()) {
            return create(releaseDataTypeDTO);
        }

        ReleaseDataTypeDTO result = releaseDataTypeDTOService.save(releaseDataTypeDTO);

        return ResponseEntity.ok().body(result);
    }

    /**
     * Find a Page of ReleaseDataType using query by example.
     */
    @RequestMapping(value = "/page", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<ReleaseDataTypeDTO>> findAll(@RequestBody PageRequestByExample<ReleaseDataTypeDTO> prbe) throws URISyntaxException {
        PageResponse<ReleaseDataTypeDTO> pageResponse = releaseDataTypeDTOService.findAll(prbe);
        return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
    }

    /**
    * Auto complete support.
    */
    @RequestMapping(value = "/complete", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReleaseDataTypeDTO>> complete(@RequestBody AutoCompleteQuery acq) throws URISyntaxException {

        List<ReleaseDataTypeDTO> results = releaseDataTypeDTOService.complete(acq.query, acq.maxResults);

        return new ResponseEntity<>(results, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete by id ${}entity.model.type}.
     */
    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) throws URISyntaxException {

        log.debug("Delete by id ReleaseDataType : {}", id);

        try {
            releaseDataTypeRepository.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely org.hibernate.exception.ConstraintViolationException
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}