package uk.co.boots.columbus.cmdb.model.rest.support;

import java.util.List;

import uk.co.boots.columbus.cmdb.model.dto.HieraDTO;

public class CsvResponse {    
	   private final String filename;
	   private final List<HieraDTO> records;

	   public CsvResponse(List<HieraDTO> records, String filename) {
	       this.records = records;
	       this.filename = filename;
	   }
	   public String getFilename() {
	       return filename;
	   }
	   public List<HieraDTO> getRecords() {
	       return records;
	   }
	}