package uk.co.boots.columbus.cmdb.model.core.rest.support;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.boots.columbus.cmdb.model.hiera.dto.HieraDTO;

public class CsvResponse {    
	   private final String filename;
	   private final List<HieraDTO> records;
	   private final Set<HieraDTO> uniqueRecords;
	   public CsvResponse(List<HieraDTO> records, String filename) {
	       this.records = records;
	       this.filename = filename;
	       uniqueRecords = null;
	   }

	   public CsvResponse(Set<HieraDTO> records, String filename) {
	       this.uniqueRecords = records;
	       this.filename = filename;
	       this.records = null;
	   }
	   
	   public String getFilename() {
	       return filename;
	   }
	   public List<HieraDTO> getRecords() {
	       return records;
	   }
	   
	   public Set<HieraDTO> getUniqueRecords(){
		   return uniqueRecords;
	   }
	}