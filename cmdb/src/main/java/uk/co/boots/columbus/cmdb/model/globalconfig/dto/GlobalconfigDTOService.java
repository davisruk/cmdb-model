/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/dto/EntityDTOService.java.e.vm
 */
package uk.co.boots.columbus.cmdb.model.globalconfig.dto;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import uk.co.boots.columbus.cmdb.model.core.dto.support.PageRequestByExample;
import uk.co.boots.columbus.cmdb.model.core.dto.support.PageResponse;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.globalconfig.repository.GlobalconfigRepository;
import uk.co.boots.columbus.cmdb.model.golbalconfig.domain.Globalconfig;
import uk.co.boots.columbus.cmdb.model.hiera.dto.CoreConfigDTO;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseDTO;
import uk.co.boots.columbus.cmdb.model.security.util.SecurityHelper;

/**
 * A simple DTO Facility for Globalconfig.
 */
@Service
public class GlobalconfigDTOService {

	@Inject
	private GlobalconfigRepository globalconfigRepository;

	@Transactional(readOnly = true)
	public GlobalconfigDTO findOne(Long id) {
		return toDTO(globalconfigRepository.findOne(id));
	}

	private void buildHieraAddresses(List<Globalconfig> cl) {
		String addr;
		String value;
		boolean allowSensitive = SecurityHelper.userCanViewSensitiveData();
		for (Globalconfig conf : cl) {
			addr = conf.getHieraAddress();
			// find Parameter in Hieara Address and replace with Parametername
			addr = addr.replaceAll("\\{ParamName\\}", conf.getParameter());
			conf.setHieraAddress(addr);
			value = conf.getValue();
			if (value != null){
				value = value.replaceAll("\\{ParamName\\}", conf.getParameter());
				if (conf.IsSensitive() && !allowSensitive)
					value = "[SENSITIVE]";
			}
			conf.setValue(value);
		}
	}

	// utility for building heira - keeps track of existing heira in a set and if it already exists
	// removes the config from the underlying cl list. use this when you need to build heira that repeats
	// the list you pass in should only be for repeating hiera - the method does not check this
	// the utility can be used at global, env, subenv and release levels you must pass in the
	// relevant domain objects for the level
	private void buildHieraAddresses(List<Globalconfig> cl, EnvironmentDTO e, SubEnvironmentDTO se, ReleaseDTO r, Set<Globalconfig> gcSet) {
		String addr;
		String value;
		boolean allowSensitive = SecurityHelper.userCanViewSensitiveData();
		for (Iterator<Globalconfig> it = cl.iterator(); it.hasNext();){
			Globalconfig conf = it.next();
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
				addr = addr.replaceAll("\\{ENVID\\}", se.subEnvironmentType.name);
				value = value.replaceAll("\\{ENVID\\}", se.subEnvironmentType.name);
			}
			if (r != null){
				addr = addr.replaceAll("\\{Release\\}", r.name);
				value = value.replaceAll("\\{Release\\}", r.name);
			}

			conf.setHieraAddress(addr);

			if (value != null) {
				if (!allowSensitive) {
					value = "[SENSITIVE]";
				}
			}
			conf.setValue(value);
			if (!gcSet.add(conf))
				it.remove();
		}
	}
	
	@Transactional(readOnly = true)
	public List<GlobalconfigDTO> findAllReplaceHiera() {
		List<Globalconfig> results = globalconfigRepository.findAll();
		buildHieraAddresses(results);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<GlobalconfigDTO> findAllReplaceHiera(Set<Globalconfig> gcSet) {
		List<Globalconfig> results = globalconfigRepository.findAll();
		buildHieraAddresses(results, null, null, null, gcSet);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<GlobalconfigDTO> findAndReplaceHieraForEnv(EnvironmentDTO eDTO, Set<Globalconfig> gcSet) {
		List<Globalconfig> results = globalconfigRepository.findByRecursiveByEnv(true);
		buildHieraAddresses(results, eDTO, null, null, gcSet);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<GlobalconfigDTO> findAndReplaceHieraForEnv() {
		List<Globalconfig> results = globalconfigRepository.findByRecursiveByEnv(true);
		buildHieraAddresses(results);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<GlobalconfigDTO> findAndReplaceHieraForSubEnv(SubEnvironmentDTO seDTO, Set<Globalconfig> gcSet) {
		List<Globalconfig> results = globalconfigRepository.findByRecursiveBySubEnv(true);
		buildHieraAddresses(results, seDTO.environment, seDTO, null, gcSet);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<GlobalconfigDTO> findAndReplaceHieraForSubEnv() {
		List<Globalconfig> results = globalconfigRepository.findByRecursiveBySubEnv(true);
		buildHieraAddresses(results);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	

	@Transactional(readOnly = true)
	public List<GlobalconfigDTO> findAndReplaceHieraForRelease(SubEnvironmentDTO seDTO, Set<Globalconfig> gcSet) {
		List<Globalconfig> results = globalconfigRepository.findByRecursiveByRel(true);
		buildHieraAddresses(results, seDTO.environment, seDTO, seDTO.release, gcSet);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	
	

	@Transactional(readOnly = true)
	public List<GlobalconfigDTO> complete(String query, int maxResults) {
		List<Globalconfig> results = globalconfigRepository.complete(query, maxResults);
		return results.stream().map(this::toDTO).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public PageResponse<GlobalconfigDTO> findAll(PageRequestByExample<GlobalconfigDTO> req) {
		Example<Globalconfig> example = null;
		Globalconfig globalconfig = toEntity(req.example);

		if (globalconfig != null) {
			example = Example.of(globalconfig);
		}

		Page<Globalconfig> page;
		if (example != null) {
			page = globalconfigRepository.findAll(example, req.toPageable());
		} else {
			page = globalconfigRepository.findAll(req.toPageable());
		}

		List<GlobalconfigDTO> content = page.getContent().stream().map(this::toDTO).collect(Collectors.toList());
		return new PageResponse<>(page.getTotalPages(), page.getTotalElements(), content);
	}

	/**
	 * Save the passed dto as a new entity or update the corresponding entity if
	 * any.
	 */
	@Transactional
	public GlobalconfigDTO save(GlobalconfigDTO dto) {
		if (dto == null) {
			return null;
		}

		Globalconfig globalconfig;
		if (dto.isIdSet()) {
			globalconfig = globalconfigRepository.findOne(dto.id);
			if (!globalconfig.IsSensitive()
					|| (globalconfig.IsSensitive() && SecurityHelper.userCanWriteSensitiveData())) {
				// only ever set sensitive data if we are allowed to
				// value is set to [SENSITIVE] when retrieved from
				// the DB for the DTO - we don't want to overwrite
				// the real value.
				globalconfig.setValue(dto.value);
				globalconfig.setSensitive(dto.sensitive);
			}
		} else {
			globalconfig = new Globalconfig();
			// sensitive data can always be set here as it's a new item
			// if the user has rights they will have been checked in the
			// UI.
			globalconfig.setValue(dto.value);
			globalconfig.setSensitive(dto.sensitive);
		}

		globalconfig.setParameter(dto.parameter);
		globalconfig.setHieraAddress(dto.hieraAddress);
		globalconfig.setNotes(dto.notes);
		globalconfig.setRecursiveByEnv(dto.recursiveByEnv);
		globalconfig.setRecursiveByRel(dto.recursiveByRel);
		globalconfig.setRecursiveBySubEnv(dto.recursiveBySubEnv);

		return toDTO(globalconfigRepository.save(globalconfig));
	}

	/**
	 * Converts the passed globalconfig to a DTO.
	 */
	public GlobalconfigDTO toDTO(Globalconfig globalconfig) {
		return toDTO(globalconfig, 1);
	}

	/**
	 * Converts the passed globalconfig to a DTO. The depth is used to control
	 * the amount of association you want. It also prevents potential infinite
	 * serialization cycles.
	 *
	 * @param globalconfig
	 * @param depth
	 *            the depth of the serialization. A depth equals to 0, means no
	 *            x-to-one association will be serialized. A depth equals to 1
	 *            means that xToOne associations will be serialized. 2 means,
	 *            xToOne associations of xToOne associations will be serialized,
	 *            etc.
	 */
	public GlobalconfigDTO toDTO(Globalconfig globalconfig, int depth) {
		if (globalconfig == null) {
			return null;
		}

		GlobalconfigDTO dto = new GlobalconfigDTO();

		dto.id = globalconfig.getId();
		dto.parameter = globalconfig.getParameter();

		// hide sensitive info if user doesn't have rights
		if (globalconfig.IsSensitive() && !SecurityHelper.userCanViewSensitiveData())
			dto.value = "[SENSITIVE]";
		else
			dto.value = globalconfig.getValue();

		dto.hieraAddress = globalconfig.getHieraAddress();
		dto.recursiveByEnv = globalconfig.isRecursiveByEnv();
		dto.recursiveByRel = globalconfig.isRecursiveByRel();
		dto.recursiveBySubEnv = globalconfig.isRecursiveBySubEnv();
		dto.notes = globalconfig.getNotes();
		dto.sensitive = globalconfig.IsSensitive();

		return dto;
	}

	/**
	 * Converts the passed dto to a Globalconfig. Convenient for query by
	 * example.
	 */
	public Globalconfig toEntity(GlobalconfigDTO dto) {
		return toEntity(dto, 1);
	}

	/**
	 * Converts the passed dto to a Globalconfig. Convenient for query by
	 * example.
	 */
	public Globalconfig toEntity(GlobalconfigDTO dto, int depth) {
		if (dto == null) {
			return null;
		}

		Globalconfig globalconfig = new Globalconfig();

		globalconfig.setId(dto.id);
		globalconfig.setParameter(dto.parameter);
		globalconfig.setValue(dto.value);
		globalconfig.setHieraAddress(dto.hieraAddress);
		globalconfig.setRecursiveByEnv(dto.recursiveByEnv);
		globalconfig.setRecursiveByRel(dto.recursiveByRel);
		globalconfig.setRecursiveBySubEnv(dto.recursiveBySubEnv);
		globalconfig.setSensitive(dto.sensitive);
		globalconfig.setNotes(dto.notes);

		return globalconfig;
	}
}