package uk.co.boots.columbus.cmdb.model.rest.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

public class CORSSupport {

	@Value("${cors.origin.host}")
	private static String corsOriginHost;
	@Value("${cors.origin.port}")
	private static String corsOriginPort;

	public static HttpHeaders createCORSHeaders(){
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "http://localhost:3000");
		headers.add("Access-Control-Allow-Origin", "http://" + corsOriginHost + ":" + corsOriginPort);
		headers.add("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		headers.add("Access-Control-Allow-Credentials", "true");
		headers.add("Access-Control-Max-Age", "3600");
		headers.add("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
		return headers;
	}
}
