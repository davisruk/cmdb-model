package uk.co.boots.columbus.cmdb.model.environment.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.core.rest.support.AutoCompleteQuery;
import uk.co.boots.columbus.cmdb.model.core.rest.support.CORSSupport;
import uk.co.boots.columbus.cmdb.model.core.rest.support.CsvResponse;
import uk.co.boots.columbus.cmdb.model.core.rest.support.YAMLResponse;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentTypeDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.environment.repository.EnvironmentRepository;
import uk.co.boots.columbus.cmdb.model.hiera.dto.HieraDTO;
import uk.co.boots.columbus.cmdb.model.hiera.dto.HieraDTOService;

@RestController
@RequestMapping("/api/environments")
public class EnvironmentResource {

    private final Logger log = LoggerFactory.getLogger(EnvironmentResource.class);

    @Inject
    private EnvironmentRepository environmentRepository;
    @Inject
    private EnvironmentDTOService environmentDTOService;
    @Inject
    private SubEnvironmentDTOService subEnvironmentDTOService;
    @Inject
    private HieraDTOService hieraDTOService;
    
    /**
     * Create a new Environment.
     */
    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EnvironmentDTO> create(@RequestBody EnvironmentDTO environmentDTO) throws URISyntaxException {

        log.debug("Create EnvironmentDTO : {}", environmentDTO);

        if (environmentDTO.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create Environment with existing ID").body(null);
        }

        EnvironmentDTO result = environmentDTOService.save(environmentDTO);

        return ResponseEntity.created(new URI("/api/environments/" + result.id)).body(result);
    }

    /**
    * Find by id Environment.
    */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EnvironmentDTO> findById(@PathVariable Long id) throws URISyntaxException {

        log.debug("Find by id Environment : {}", id);

        EnvironmentDTO dto = environmentDTOService.findOne(id);
        return Optional.ofNullable(dto).map(environmentDTO -> new ResponseEntity<>(environmentDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @RequestMapping(value = "/envTypes", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EnvironmentTypeDTO>> getAllEnvironmentTypes() throws URISyntaxException {

        log.debug("Find all EnvironmentTypes");

        List<EnvironmentTypeDTO> dtoList = environmentDTOService.findAllEnvironmentTypes();
        return Optional.ofNullable(dtoList).map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/subconfigdownload/{id}", method = POST, produces = "text/csv")
    @ResponseBody // indicate to use a compatible HttpMessageConverter
    public CsvResponse downloadConfigsBySubEnv(@PathVariable Long id) throws IOException {
    	return new CsvResponse(hieraDTOService.findHieraCompleteInfoForSubEnv(id, true), "HieraData_SubEnv.csv");
    }

    @RequestMapping(value = "/configdownloadall/{id}", method = POST, produces = "text/csv")
    @ResponseBody // indicate to use a compatible HttpMessageConverter
    public CsvResponse downloadConfigsAllPost(@PathVariable Long id) throws IOException {
    	return new CsvResponse(hieraDTOService.getHieraCompleteInfoForEnvWithSubstitution(id, new HashSet<HieraDTO>()), "HieraData_Env.csv");
    }

    @RequestMapping(value = "/configdownloadall", method = POST, produces = "text/csv")
    @ResponseBody // indicate to use a compatible HttpMessageConverter
    public CsvResponse downloadConfigsAllEnvironmentsPost() throws IOException {
    	return new CsvResponse(hieraDTOService.getHieraForAllEnvsWithSubstitution(), "HieraData_Complete.csv");
    }

    @RequestMapping(value = "/configdownloadyaml", method = POST, produces = "text/plain")
    @ResponseBody // indicate to use a compatible HttpMessageConverter
    public YAMLResponse downloadConfigsAllEnvironmentsAsYAML() throws IOException {
    	return new YAMLResponse ("config.yaml", hieraDTOService.getConfigAsYaml(hieraDTOService.getHieraForAllEnvsWithSubstitution()));
    }

    @RequestMapping(value = "/config/yaml", method = POST)
    public ResponseEntity<String> getConfigsAllEnvironmentsAsYAML() throws IOException {
    	return new ResponseEntity<String> (hieraDTOService.getConfigAsYaml(hieraDTOService.getHieraForAllEnvsWithSubstitution()), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EnvironmentDTO>> all() throws URISyntaxException {

        List<EnvironmentDTO> results = environmentDTOService.findAllEnvironments();

        return new ResponseEntity<>(results, CORSSupport.createCORSHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/notinpageable", method = POST, produces = APPLICATION_JSON_VALUE, consumes= APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<SubEnvironmentDTO>> findAndPageAvailableEnvironments(@RequestBody PageRequestByExample<EnvironmentDTO> prbe) throws URISyntaxException {

        log.debug("Find Sub Environments not in Environment : {}", prbe);
        PageResponse<SubEnvironmentDTO> results = subEnvironmentDTOService.getSubEnvsNotInListForEnvironment(prbe);
        return new ResponseEntity<>(results, CORSSupport.createCORSHeaders(), HttpStatus.OK);
    }
    
    /**
     * Update Environment.
     */
    @RequestMapping(value = "/", method = PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EnvironmentDTO> update(@RequestBody EnvironmentDTO environmentDTO) throws URISyntaxException {

        log.debug("Update EnvironmentDTO : {}", environmentDTO);

        if (!environmentDTO.isIdSet()) {
            return create(environmentDTO);
        }

        EnvironmentDTO result = environmentDTOService.save(environmentDTO);

        return ResponseEntity.ok().body(result);
    }

    /**
     * Find a Page of Environment using query by example.
     */
    @RequestMapping(value = "/page", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<EnvironmentDTO>> findAll(@RequestBody PageRequestByExample<EnvironmentDTO> prbe) throws URISyntaxException {
        PageResponse<EnvironmentDTO> pageResponse = environmentDTOService.findAll(prbe);
        return new ResponseEntity<>(pageResponse, new HttpHeaders(), HttpStatus.OK);
    }

    /**
    * Auto complete support.
    */
    @RequestMapping(value = "/complete", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EnvironmentDTO>> complete(@RequestBody AutoCompleteQuery acq) throws URISyntaxException {

        List<EnvironmentDTO> results = environmentDTOService.complete(acq.query, acq.maxResults);

        return new ResponseEntity<>(results, new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete by id ${}entity.model.type}.
     */
    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Long id) throws URISyntaxException {

        log.debug("Delete by id Environment : {}", id);

        try {
            environmentRepository.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely org.hibernate.exception.ConstraintViolationException
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}