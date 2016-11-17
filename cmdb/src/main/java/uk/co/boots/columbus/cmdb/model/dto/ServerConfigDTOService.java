package uk.co.boots.columbus.cmdb.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.domain.Environment;
import uk.co.boots.columbus.cmdb.model.domain.Server;
import uk.co.boots.columbus.cmdb.model.domain.ServerConfig;
import uk.co.boots.columbus.cmdb.model.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.repository.ServerConfigRepository;
import uk.co.boots.columbus.cmdb.model.repository.ServerRepository;

/**
 * A simple DTO Facility for ServerConfig.
 */
@Service
public class ServerConfigDTOService {

    @Inject
    private ServerConfigRepository serverConfigRepository;
    @Inject
    private ServerDTOService serverDTOService;
    @Inject
    private ServerRepository serverRepository;

    @Transactional(readOnly = true)
    public ServerConfigDTO findOne(Long id) {
        return toDTO(serverConfigRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<ServerConfigDTO> complete(String query, int maxResults) {
        List<ServerConfig> results = serverConfigRepository.complete(query, maxResults);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private void buildHieraAddresses (List<ServerConfig> cl){
    	String addr;
    	for (ServerConfig conf: cl){
        	addr = conf.getHieraAddress();
        	//find Parameter in Hieara Address and replace with Parametername
        	addr = addr.replaceAll("\\{ParamName\\}",conf.getParameter());
        	//find ServerTypeTag in Hieara Address and replace
        	addr = addr.replaceAll("\\{ServType\\}", conf.getServer().getServerType().getName());
        	// find EnvTag in Hieara Address and replace with Env.name
        	// even though this is many to many servers always have the same configuration
        	// therefore we just take the first environment value in the list
        	List<Environment> envs = conf.getServer().getEnvironments(); 
        	if (envs != null && !envs.isEmpty())
        		addr = addr.replaceAll("\\{ENVID\\}", envs.get(0).getName());
        	conf.setHieraAddress(addr);
        }
    }

    @Transactional(readOnly = true)
    public List<ServerConfigDTO> findByServerName(String query) {
        List<ServerConfig> results = serverConfigRepository.findByServerName(query);
        buildHieraAddresses (results);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    
    @Transactional(readOnly = true)
    public List<ServerConfigDTO> findByServerEnvironmentName(String query) {
        List<ServerConfig> results = serverConfigRepository.findByServer_environments_name(query);
        buildHieraAddresses (results);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ServerConfigDTO> findByServerEnvironmentReleaseName(String envName) {
        List<ServerConfig> results = serverConfigRepository.findByServer_environments_release_name(envName);
        buildHieraAddresses (results);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PageResponse<ServerConfigDTO> findAll(PageRequestByExample<ServerConfigDTO> req) {
        Example<ServerConfig> example = null;
        ServerConfig serverConfig = toEntity(req.example);

        if (serverConfig != null) {
            example = Example.of(serverConfig);
        }

        Page<ServerConfig> page;
        if (example != null) {
            page = serverConfigRepository.findAll(example, req.toPageable());
        } else {
            page = serverConfigRepository.findAll(req.toPageable());
        }

        List<ServerConfigDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
        return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
    }

    /**
     * Save the passed dto as a new entity or update the corresponding entity if any.
     */
    @Transactional
    public ServerConfigDTO save(ServerConfigDTO dto) {
        if (dto == null) {
            return null;
        }

        ServerConfig serverConfig;
        if (dto.isIdSet()) {
            serverConfig = serverConfigRepository.findOne(dto.id);
        } else {
            serverConfig = new ServerConfig();
        }

        serverConfig.setParameter(dto.parameter);
        serverConfig.setValue(dto.value);
        serverConfig.setHieraAddress(dto.hieraAddress);

        if (dto.server == null) {
            serverConfig.setServer(null);
        } else {
            Server server = serverConfig.getServer();
            if (server == null || (server.getId().compareTo(dto.server.id) != 0)) {
                serverConfig.setServer(serverRepository.findOne(dto.server.id));
            }
        }

        return toDTO(serverConfigRepository.save(serverConfig));
    }

    /**
     * Converts the passed serverConfig to a DTO.
     */
    public ServerConfigDTO toDTO(ServerConfig serverConfig) {
        return toDTO(serverConfig, 1);
    }

    /**
     * Converts the passed serverConfig to a DTO. The depth is used to control the
     * amount of association you want. It also prevents potential infinite serialization cycles.
     *
     * @param serverConfig
     * @param depth the depth of the serialization. A depth equals to 0, means no x-to-one association will be serialized.
     *              A depth equals to 1 means that xToOne associations will be serialized. 2 means, xToOne associations of
     *              xToOne associations will be serialized, etc.
     */
    public ServerConfigDTO toDTO(ServerConfig serverConfig, int depth) {
        if (serverConfig == null) {
            return null;
        }

        ServerConfigDTO dto = new ServerConfigDTO();

        dto.id = serverConfig.getId();
        dto.parameter = serverConfig.getParameter();
        dto.value = serverConfig.getValue();
        dto.hieraAddress = serverConfig.getHieraAddress();
        if (depth-- > 0) {
            dto.server = serverDTOService.toDTO(serverConfig.getServer(), depth);
        }

        return dto;
    }

    /**
     * Converts the passed dto to a ServerConfig.
     * Convenient for query by example.
     */
    public ServerConfig toEntity(ServerConfigDTO dto) {
        return toEntity(dto, 1);
    }

    /**
     * Converts the passed dto to a ServerConfig.
     * Convenient for query by example.
     */
    public ServerConfig toEntity(ServerConfigDTO dto, int depth) {
        if (dto == null) {
            return null;
        }

        ServerConfig serverConfig = new ServerConfig();

        serverConfig.setId(dto.id);
        serverConfig.setParameter(dto.parameter);
        serverConfig.setValue(dto.value);
        serverConfig.setHieraAddress(dto.hieraAddress);
        if (depth-- > 0) {
            serverConfig.setServer(serverDTOService.toEntity(dto.server, depth));
        }

        return serverConfig;
    }
}