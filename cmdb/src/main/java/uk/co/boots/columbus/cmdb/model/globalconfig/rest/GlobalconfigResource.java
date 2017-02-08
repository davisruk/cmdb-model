package uk.co.boots.columbus.cmdb.model.globalconfig.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.core.rest.support.AutoCompleteQuery;
import uk.co.boots.columbus.cmdb.model.core.rest.support.CsvResponse;
import uk.co.boots.columbus.cmdb.model.globalconfig.dto.GlobalconfigDTO;
import uk.co.boots.columbus.cmdb.model.globalconfig.dto.GlobalconfigDTOService;
import uk.co.boots.columbus.cmdb.model.globalconfig.repository.GlobalconfigRepository;
import uk.co.boots.columbus.cmdb.model.hiera.dto.HieraDTOService;

@RestController
@RequestMapping("/api/globalconfigs")
public class GlobalconfigResource {

    private final Logger log = LoggerFactory.getLogger(GlobalconfigResource.class);

    @Inject
    private GlobalconfigRepository globalconfigRepository;
    @Inject
    private GlobalconfigDTOService globalconfigDTOService;
    @Inject
    private HieraDTOService hieraDTOService;
    /**
     * Create a new Globalconfig.
     */
    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalconfigDTO> create(@RequestBody GlobalconfigDTO globalconfigDTO) throws URISyntaxException {

        log.debug("Create GlobalconfigDTO : {}", globalconfigDTO);

        if (globalconfigDTO.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create Globalconfig with existing ID").body(null);
        }

        GlobalconfigDTO result = globalconfigDTOService.save(globalconfigDTO);

        return ResponseEntity.created(new URI("/api/globalconfigs/" + result.id)).body(result);
    }

    /**
    * Find by id Globalconfig.
    */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalconfigDTO> findById(@PathVariable Long id) throws URISyntaxException {

        log.debug("Find by id Globalconfig : {}", id);

        return Optional.ofNullable(globalconfigDTOService.findOne(id)).map(globalconfigDTO -> new ResponseEntity<>(globalconfigDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @RequestMapping(value = "/configdownload", method = GET, produces = "text/csv")
    @ResponseBody // indicate to use a compatible HttpMessageConverter
    public CsvResponse downloadConfigsByReleaseName() throws IOException {
    	return new CsvResponse(hieraDTOService.findHieraInfoForGlobalconfig(), "HieraData_Global_Config.csv");
    }


    /**
     * Update Globalconfig.
     */
    @RequestMapping(value = "/", method = PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalconfigDTO> update(@RequestBody GlobalconfigDTO globalconfigDTO) throws URISyntaxException {

        log.debug("Update GlobalconfigDTO : {}", globalconfigDTO);

        if (!globalconfigDTO.isIdSet()) {
            return create(globalconfigDTO);
        }

        GlobalconfigDTO result = globalconfigDTOService.save(globalconfigDTO);

        return ResponseEntity.ok().body(result);
    }

    /**
     * Find a Page of Globalconfig using query by example.
     */
    @RequestMapping(value = "/page", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<GlobalconfigDTO>> findAll(@RequestBody PageRequestByExample<GlobalconfigDTO> prbe) throws URISyntaxException {
        PageResponse<GlobalconfigDTO> pageResponse = globalconfigDTOService.findAll(prbe);
        return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
    }

    /**
    * Auto complete support.
    */
    @RequestMapping(value = "/complete", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GlobalconfigDTO>> complete(@RequestBody AutoCompleteQuery acq) throws URISyntaxException {

        List<GlobalconfigDTO> results = globalconfigDTOService.complete(acq.query, acq.maxResults);

        return new ResponseEntity<>(results, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete by id ${}entity.model.type}.
     */
    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) throws URISyntaxException {

        log.debug("Delete by id Globalconfig : {}", id);

        try {
            globalconfigRepository.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely org.hibernate.exception.ConstraintViolationException
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}