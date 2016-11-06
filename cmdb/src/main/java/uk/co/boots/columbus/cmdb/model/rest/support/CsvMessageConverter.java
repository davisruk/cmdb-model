package uk.co.boots.columbus.cmdb.model.rest.support;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.opencsv.CSVWriter;

import uk.co.boots.columbus.cmdb.model.dto.HieraDTOService;

public class CsvMessageConverter extends AbstractHttpMessageConverter<CsvResponse> {

	public static final MediaType MEDIA_TYPE = new MediaType("text", "csv", Charset.forName("utf-8"));
	   public CsvMessageConverter() {
	       super(MEDIA_TYPE);
	   }

	   protected boolean supports(Class<?> clazz) {
	       return CsvResponse.class.equals(clazz);
	   }

	   protected void writeInternal(CsvResponse csvResponse, HttpOutputMessage output) throws IOException, HttpMessageNotWritableException {
	       output.getHeaders().setContentType(MEDIA_TYPE);
	       output.getHeaders().set("Content-Disposition", "attachment; filename=\"" + csvResponse.getFilename() + "\"");
	       OutputStream out = output.getBody();
	       CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(out), ',');
	       List<String[]> result = new HieraDTOService().convertForCSV(csvResponse.getRecords(), true); 
	       csvWriter.writeAll(result,false);
           csvWriter.flush();
           csvWriter.close();	       
	   }

	@Override
	protected CsvResponse readInternal(Class<? extends CsvResponse> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}
	}