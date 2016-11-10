package uk.co.boots.columbus.cmdb.model.rest.support;

import org.springframework.http.HttpHeaders;

public class CORSSupport {
	public static HttpHeaders createCORSHeaders(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
		headers.add("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		headers.add("Access-Control-Allow-Credentials", "true");
		headers.add("Access-Control-Max-Age", "3600");
		headers.add("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
		return headers;
	}
}
