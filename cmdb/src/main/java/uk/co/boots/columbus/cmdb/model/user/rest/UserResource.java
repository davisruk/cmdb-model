package uk.co.boots.columbus.cmdb.model.user.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.core.rest.support.AutoCompleteQuery;
import uk.co.boots.columbus.cmdb.model.core.rest.support.CORSSupport;
import uk.co.boots.columbus.cmdb.model.core.storage.StorageService;
import uk.co.boots.columbus.cmdb.model.user.dto.UserDTO;
import uk.co.boots.columbus.cmdb.model.user.dto.UserDTOService;
import uk.co.boots.columbus.cmdb.model.user.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserResource {

	@Inject
	private UserRepository userRepo;
	@Inject
	private UserDTOService userDTOService;
	@Inject 
	private StorageService storageService;
	
    private final Logger log = LoggerFactory.getLogger(UserResource.class);	
	/**
     * Create a new User.
     */
    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) throws URISyntaxException {

        log.debug("Create UserDTO : {}", userDTO);

        if (userDTO.isIdSet()) {
            return ResponseEntity.badRequest().header("Failure", "Cannot create User with existing ID").body(null);
        }

        UserDTO result = userDTOService.save(userDTO);

        return ResponseEntity.created(new URI("/api/users/" + result.id)).body(result);
    }

    /**
    * Find by id User.
    */
    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) throws URISyntaxException {

        log.debug("Find by id User : {}", id);

        return Optional.ofNullable(userDTOService.findOne(id)).map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update User.
     */
    @RequestMapping(value = "/", method = PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) throws URISyntaxException {

        log.debug("Update ServerDTO : {}", userDTO);

        if (!userDTO.isIdSet()) {
            return create(userDTO);
        }

        UserDTO result = userDTOService.save(userDTO);

        return ResponseEntity.ok().body(result);
    }

    /**
     * Find a Page of Server using query by example.
     */
    @RequestMapping(value = "/page", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse<UserDTO>> findAll(@RequestBody PageRequestByExample<UserDTO> prbe) throws URISyntaxException {
        PageResponse<UserDTO> pageResponse = userDTOService.findAll(prbe);
        return new ResponseEntity<>(pageResponse, CORSSupport.createCORSHeaders(), HttpStatus.OK);
    }

    /**
    * Auto complete support.
    */
    @RequestMapping(value = "/complete", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> complete(@RequestBody AutoCompleteQuery acq) throws URISyntaxException {

        List<UserDTO> results = userDTOService.complete(acq.query, acq.maxResults);

        return new ResponseEntity<>(results, CORSSupport.createCORSHeaders(), HttpStatus.OK);
    }

    /**
     * Delete by id ${}entity.model.type}.
     */
    @RequestMapping(value = "/{id}", method = DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {

        log.debug("Delete by id User : {}", id);

        try {
            userRepo.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely org.hibernate.exception.ConstraintViolationException
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
    
    @RequestMapping(value = "/photoUpload", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handleUpload(@RequestParam("photo[]") MultipartFile file) throws URISyntaxException {
    	storageService.store(file);
    	return new ResponseEntity<String>("Success!", CORSSupport.createCORSHeaders(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/photoDownload", method = GET)
    public ResponseEntity<byte[]> getPhoto() throws IOException {
        Resource res = storageService.loadAsResource("ms_store_BS!!.png");
        File imgPath = res.getFile();
        byte[] image = Files.readAllBytes(imgPath.toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }    
}
