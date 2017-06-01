package uk.co.boots.columbus.cmdb.model.hiera.dto;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;

import uk.co.boots.columbus.cmdb.model.component.dto.ComponentConfigDTOService;
import uk.co.boots.columbus.cmdb.model.core.rest.support.CsvResponse;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.EnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentConfigDTOService;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTO;
import uk.co.boots.columbus.cmdb.model.environment.dto.SubEnvironmentDTOService;
import uk.co.boots.columbus.cmdb.model.globalconfig.dto.GlobalconfigDTOService;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseConfigDTO;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseConfigDTOService;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerConfigDTO;
import uk.co.boots.columbus.cmdb.model.server.dto.ServerConfigDTOService;

@Service
public class HieraDTOService implements Comparator<HieraDTO> {

	@Inject
	private GlobalconfigDTOService gcService;
	@Inject
	private ServerConfigDTOService scService;
	@Inject
	private ReleaseConfigDTOService rcService;
	@Inject
	private ComponentConfigDTOService ccService;
	@Inject
	private EnvironmentDTOService eDTOService;
	@Inject
	private SubEnvironmentConfigDTOService secDTOService;
	@Inject
	private SubEnvironmentDTOService seDTOService;

	// helper function to cope with the poor DTO structure
	// Each ConfigDTO implements CoreConfigDTO that exposes accessors
	private List<HieraDTO> addToHieraDTOList(List<HieraDTO> hieraList, List<? extends CoreConfigDTO> fromList) {
		for (CoreConfigDTO cDTO : fromList)
			hieraList.add(new HieraDTO(cDTO.getValue(), cDTO.getHieraAddress()));

		return hieraList;
	}

	private Set<HieraDTO> addToHieraDTOSet(Set<HieraDTO> hieraSet, List<? extends CoreConfigDTO> fromList) {
		for (CoreConfigDTO cDTO : fromList)
			hieraSet.add(new HieraDTO(cDTO.getValue(), cDTO.getHieraAddress()));

		return hieraSet;
	}

	public List<HieraDTO> findHieraInfoForGlobalconfig() {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		return hDTOList;
	}

	public List<HieraDTO> findHieraCompleteInfoForSubEnv(Long subEnvId, boolean includeGlobal) {
		SubEnvironmentDTO subEnv = seDTOService.findOne(subEnvId, 1);
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		if (includeGlobal)
			addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		else
			addToHieraDTOList(hDTOList, gcService.findAndReplaceHieraForSubEnv());

		if (subEnv.subEnvironmentType != null)
			addToHieraDTOList(hDTOList, secDTOService.findByTypeAndEnvironmentName(subEnv.subEnvironmentType.name,
					subEnv.environment.name));
		if (subEnv.release != null) {
			addToHieraDTOList(hDTOList, ccService.findByComponentPackageReleaseName(subEnv.release.name));
		}
		
		return hDTOList;
	}

	

	private List<HieraDTO> findHieraCompleteInfoForEnv(Long EnvId, boolean includeGlobal) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		if (includeGlobal)
			addToHieraDTOList(hDTOList, gcService.findAllReplaceHiera());
		else{
			addToHieraDTOList(hDTOList, gcService.findAndReplaceHieraForEnv());
		}
			
		EnvironmentDTO dto = eDTOService.findOne(EnvId);
		List<ReleaseConfigDTO> relDTOs = rcService.getDistinctConfigsForEnv(dto.name);
		// Check recursive flag and add to list for releases
		addToHieraDTOList(hDTOList, relDTOs);
		for (SubEnvironmentDTO seDTO : dto.subEnvironments) {
			seDTO.environment = dto;
			hDTOList.addAll(findHieraCompleteInfoForSubEnv(seDTO.id, false));
			// add in recursive release config items here
			addToHieraDTOList(hDTOList, secDTOService.getSubEnvironmentDTOsWithHieraAddressesForRecursiveReleaseItems(seDTO, relDTOs));
		}
		List<ServerConfigDTO> serverConfDTOs = scService.findByDistinctServerSubEnvironmentEnvironment(dto.id);
		addToHieraDTOList(hDTOList, serverConfDTOs);
		return hDTOList;
	}
	
	public Set<HieraDTO> getHieraForAllEnvsWithSubstitution(){
		Set<HieraDTO> hDTOSet = new HashSet<HieraDTO>();
		addToHieraDTOSet(hDTOSet, gcService.findAllReplaceHiera());
		List<EnvironmentDTO> dtoList = eDTOService.findAllEnvironments();
		for (EnvironmentDTO dto:dtoList){
			hDTOSet.addAll(findHieraCompleteInfoForEnv(dto.id, false));
		}		
		return hDTOSet;
	}
	
	public Set<HieraDTO> getHieraCompleteInfoForEnvWithSubstitution(Long EnvId, Set<HieraDTO> hDTOSet) {
		// we may have already added this, but the set will take care of it anyway and we may have repeating elements
		addToHieraDTOSet(hDTOSet, gcService.findAllReplaceHiera());
		EnvironmentDTO dto = eDTOService.findOne(EnvId);
		List<ReleaseConfigDTO> relDTOs = rcService.getDistinctConfigsForEnv(dto.name);
		// Check recursive flag and add to list for releases
		addToHieraDTOSet(hDTOSet, relDTOs);
		for (SubEnvironmentDTO seDTO : dto.subEnvironments) {
			seDTO.environment = dto;
			hDTOSet.addAll(findHieraCompleteInfoForSubEnv(seDTO.id, false));
			// add in recursive release config items here
			addToHieraDTOSet(hDTOSet, secDTOService.getSubEnvironmentDTOsWithHieraAddressesForRecursiveReleaseItems(seDTO, relDTOs));
		}
		List<ServerConfigDTO> serverConfDTOs = scService.findByDistinctServerSubEnvironmentEnvironment(dto.id);
		addToHieraDTOSet(hDTOSet, serverConfDTOs);
		return hDTOSet;
	}
	
	public List<HieraDTO> findHieraInfoForServer(String serverName) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, scService.findByServerName(serverName));
		return hDTOList;
	}

	public List<HieraDTO> findHieraInfoForRelease(String relName) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, rcService.findByReleaseName(relName));
		return hDTOList;
	}

	public List<HieraDTO> findHieraInfoForSolutionComponent(Long compId) {
		List<HieraDTO> hDTOList = new ArrayList<HieraDTO>();
		addToHieraDTOList(hDTOList, ccService.findBySolutionComponentId(compId));
		return hDTOList;
	}

	public List<String[]> convertForCSV(CsvResponse csv, boolean includeHeaders) {
		Collection<HieraDTO> coll = csv.getRecords();
		if (coll == null)
			coll = csv.getUniqueRecords();

		List<String[]> result = new ArrayList<String[]>();
		if (includeHeaders)
			result.add(new String[] { "Address", "Value" });
		for (HieraDTO h : coll) {
			result.add(new String[] { h.address, h.value });
		}
		return result;
	}

	@Override
	public int compare(HieraDTO o1, HieraDTO o2) {
		return o1.address.compareTo(o2.address);
	}
	
	public String getConfigAsYaml(Set<HieraDTO> dataSet){
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory().enable(Feature.MINIMIZE_QUOTES));
		StringWriter sw = new StringWriter();
		List<HieraDTO> dl = new ArrayList<HieraDTO>(dataSet);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		Comparator<HieraDTO> comparator = (HieraDTO h1, HieraDTO h2)->h1.address.compareTo(h2.address);
		dl.sort(comparator);
		JsonNode root = mapper.createObjectNode();
		for (int i = 0; i < dl.size(); i++){
			processYAMLTree (mapper, root, dl, i);			
		}

		try{
			JsonGenerator generator = mapper.getFactory().createGenerator(sw);
			mapper.writeTree(generator, root);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	private void processYAMLTree(ObjectMapper mapper, JsonNode root, List<HieraDTO> configList, int index){
		// break the address down into nodes
		HieraDTO cc = configList.get(index);
		List<String> nodes = Arrays.asList(cc.address.split(":"));
		JsonNode base = root;
		for (int i = 0; i<nodes.size()-1;i++){
			String nodeName = nodes.get(i);
			if (! nodeName.equals("ROOT")){
				JsonNode traverser = base.get(nodeName); 
				if (traverser == null){
					JsonNode node = mapper.createObjectNode();
					((ObjectNode)base).set(nodeName, node);
					base = node;
				}
				else
					base = traverser;
			}
		}
		
		// check if this field needs to be setup as an array
		String nodeName = nodes.get(nodes.size()-1);
		JsonNode fieldNode = base.get(nodeName);
		JsonNode arrayNode = null;
		if (fieldNode != null){
			arrayNode = fieldNode;
			// the field already exists
			// we need to convert it to an array
			// check it first though as we may have already created it
			if (!arrayNode.isArray()){
				// create the array
				((ObjectNode)base).remove(nodeName);
				arrayNode = mapper.createArrayNode();
				((ObjectNode)base).set(nodeName, arrayNode);
				((ArrayNode)arrayNode).add(fieldNode);
				
			}
			// at this point we have an array node
			// add the value to it - this only works with a depth of 1!
			// if the array needs to contain a node it won't work!
			((ArrayNode)arrayNode).add(cc.value);
		}
		else{
			// we aren't dealing with an array - yet
			// just add the value to the node.
			((ObjectNode)base).put(nodeName, cc.value);			
		}
	}
	
	
}
