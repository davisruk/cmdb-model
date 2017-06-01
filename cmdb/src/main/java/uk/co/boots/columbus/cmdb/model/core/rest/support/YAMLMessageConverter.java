package uk.co.boots.columbus.cmdb.model.core.rest.support;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class YAMLMessageConverter extends AbstractHttpMessageConverter<YAMLResponse> {

	public static final MediaType MEDIA_TYPE = new MediaType("text", "plain", Charset.forName("utf-8"));
	   public YAMLMessageConverter() {
	       super(MEDIA_TYPE);
	   }

	   protected boolean supports(Class<?> clazz) {
	       return YAMLResponse.class.equals(clazz);
	   }

	   protected void writeInternal(YAMLResponse yamlResponse, HttpOutputMessage output) throws IOException, HttpMessageNotWritableException {
	       output.getHeaders().setContentType(MEDIA_TYPE);
	       output.getHeaders().set("Content-Disposition", "attachment; filename=\"" + yamlResponse.getFilename() + "\"");
	       OutputStream out = output.getBody();
	       // shitty fix until I work out why ObjectMapper.writeTree is ignoring @JsonRawValue
	       String yaml = yamlResponse.getYAMLString();
	       yaml = yaml.replace("\\\\", "\\"); // replace \\ with \
	       yaml = yaml.replace("'\"", "\""); // replace '" with "
	       yaml = yaml.replace("\"'", "\""); // replace "' with "
	       yaml = yaml.replace("'''", "'");
	       // end of shitty fix
	       //System.out.println("YAML config:");
	       //System.out.println(yaml);
	       IOUtils.write(yaml, out, Charset.forName("utf-8"));
	       out.flush();
	       out.close();
	   }

	@Override
	protected YAMLResponse readInternal(Class<? extends YAMLResponse> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}
}
