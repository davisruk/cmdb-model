package uk.co.boots.columbus.cmdb.model.core.rest.support;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class YAMLResponse {
	private final String filename;
	private final String YAMLString;
	public YAMLResponse(String filename, String yaml) {
		this.YAMLString = yaml;
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public String getYAMLString() {
		return YAMLString;
	}

}
