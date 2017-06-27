package uk.co.boots.columbus.cmdb.model.server.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.environment.domain.SubEnvironment;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.repository.SubEnvironmentRepository;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseDTO;
import uk.co.boots.columbus.cmdb.model.security.util.SecurityHelper;
import uk.co.boots.columbus.cmdb.model.server.domain.Server;
import uk.co.boots.columbus.cmdb.model.server.domain.ServerConfig;
import uk.co.boots.columbus.cmdb.model.server.repository.ServerConfigRepository;
import uk.co.boots.columbus.cmdb.model.server.repository.ServerRepository;

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
    @Inject
    private SubEnvironmentRepository seRepository;


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
    	String value;
		boolean allowSensitive = SecurityHelper.userCanViewSensitiveData();
		
    	for (ServerConfig conf: cl){
        	addr = conf.getHieraAddress();
        	value = conf.getValue();
        	if (addr != null){
	        	addr = addr.replaceAll("\\{ParamName\\}",conf.getParameter());
	        	addr = addr.replaceAll("\\{ServType\\}", conf.getServer().getServerType().getName());
        	}
        	if (value != null){
	        	value = value.replaceAll("\\{ServType\\}", conf.getServer().getServerType().getName());
	        	value = value.replaceAll("\\{ParamName\\}",conf.getParameter());
        	}
        	// find EnvTag in Hieara Address and replace with Env.name
        	// even though this is many to many servers always have the same configuration
        	// therefore we just take the first environment value in the list
        	List<SubEnvironment> envs = seRepository.findSubEnvsOfServer(conf.getServer().getId());
        	if (envs != null && !envs.isEmpty()){
        		if (addr!=null)
        			addr = addr.replaceAll("\\{ENVID\\}", envs.iterator().next().getEnvironment().getName());
        		if (value!=null)
        			value = value.replaceAll("\\{ENVID\\}", envs.iterator().next().getEnvironment().getName());
        	}

        	if (conf.IsSensitive() && !allowSensitive){
        		value = "[SENSITIVE]";
        	}
        	conf.setHieraAddress(addr);
        	conf.setValue(value);
        }
    }

	public List<ServerConfigDTO> populateHieraAddresses(List<ServerConfigDTO> scl, EnvironmentDTO e, SubEnvironmentDTO se, ReleaseDTO rel) {
		String addr;
		String value;
		List<ServerConfigDTO> populatedConfig = new ArrayList<ServerConfigDTO>();
		boolean allowSensitive = SecurityHelper.userCanViewSensitiveData();
		for (Iterator<ServerConfigDTO> it = scl.iterator(); it.hasNext();){
			ServerConfigDTO conf = it.next();
			ServerConfigDTO populatedConf = conf.getCopy();
			addr = conf.getHieraAddress();
			value = conf.getValue();
			// find Parameter in Hieara Address and replace with Parametername
			addr = addr.replaceAll("\\{ParamName\\}", conf.getParameter());
			value = value.replaceAll("\\{ParamName\\}", conf.getParameter());
			if (e != null){
				addr = addr.replaceAll("\\{ENVID\\}", e.name);
				value = value.replaceAll("\\{ENVID\\}", e.name);
			}
			if (se != null){
				addr = addr.replaceAll("\\{SubEnvType\\}", se.subEnvironmentType.name);
				value = value.replaceAll("\\{SubEnvType\\}", se.subEnvironmentType.name);
			}
			if (rel != null){
				addr = addr.replaceAll("\\{Release\\}", rel.name);
				value = value.replaceAll("\\{Release\\}", rel.name);
			}
				

			addr = addr.replaceAll("\\{ServType\\}", conf.server.serverType.name);
			value = value.replaceAll("\\{ServType\\}", conf.server.serverType.name);
			addr = addr.replaceAll("\\{ServerName\\}", conf.server.name);
			value = value.replaceAll("\\{ServerName\\}", conf.server.name);

			populatedConf.hieraAddress = addr;

			if (value != null) {
				if (!allowSensitive && conf.sensitive) {
					value = "[SENSITIVE]";
				}
			}
			populatedConf.value = value;
			populatedConfig.add(populatedConf);
		}
		return populatedConfig;
	}

    @Transactional(readOnly = true)
    public List<ServerConfigDTO> findByServerName(String query) {
        List<ServerConfig> results = serverConfigRepository.findByServerName(query);
        buildHieraAddresses (results);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    
    @Transactional(readOnly = true)
    public List<ServerConfigDTO> findByServerSubEnvironmentName(String query) {
        List<ServerConfig> results = serverConfigRepository.findByServer_nodeSubEnvironments_subEnvironment_environment_name(query);
        buildHieraAddresses (results);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ServerConfigDTO> findByDistinctServerSubEnvironmentEnvironment(Long envId) {
    	List<ServerConfig> results = serverConfigRepository.findDistinctByServer_nodeSubEnvironments_subEnvironment_environment_id(envId);
        buildHieraAddresses (results);
        return results.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ServerConfigDTO> getServerConfigsForSubEnvTypeAndEnv(Long envId, String subEnvType) {
    	List<ServerConfig> results = serverConfigRepository.findDistinctByServer_nodeSubEnvironments_subEnvironment_subEnvironmentType_nameAndServer_nodeSubEnvironments_subEnvironment_environment_id(subEnvType, envId);
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
			if (!serverConfig.IsSensitive()
					|| (serverConfig.IsSensitive() && SecurityHelper.userCanWriteSensitiveData())) {
				// only ever set sensitive data if we are allowed to
				// value is set to [SENSITIVE] when retrieved from
				// the DB for the DTO - we don't want to overwrite
				// the real value.
				serverConfig.setValue(dto.value);
				serverConfig.setSensitive(dto.sensitive);
			}
		} else {
			serverConfig = new ServerConfig();
			// sensitive data can always be set here as it's a new item
			// if the user has rights they will have been checked in the
			// UI.
			serverConfig.setValue(dto.value);
			serverConfig.setSensitive(dto.sensitive);
		}

        serverConfig.setParameter(dto.parameter);
        serverConfig.setHieraAddress(dto.hieraAddress);
        serverConfig.setNotes(dto.notes);
        serverConfig.setArrayItem(dto.arrayItem);
        
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
        //return toDTO(serverConfig, 1);
    	return toDTO(serverConfig, 2);
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
		// hide sensitive info if user doesn't have rights
		if (serverConfig.IsSensitive() && !SecurityHelper.userCanViewSensitiveData())
			dto.value = "[SENSITIVE]";
		else
			dto.value = serverConfig.getValue();

        dto.hieraAddress = serverConfig.getHieraAddress();
		dto.notes = serverConfig.getNotes();
		dto.sensitive = serverConfig.IsSensitive();
		dto.arrayItem = serverConfig.isArrayItem();
        
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
        serverConfig.setSensitive(dto.sensitive);
        serverConfig.setNotes(dto.notes);
        serverConfig.setArrayItem(dto.arrayItem);
        
        if (depth-- > 0) {
            serverConfig.setServer(serverDTOService.toEntity(dto.server, depth));
        }

        return serverConfig;
    }
}