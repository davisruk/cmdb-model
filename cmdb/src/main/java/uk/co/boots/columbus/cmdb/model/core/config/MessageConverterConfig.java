package uk.co.boots.columbus.cmdb.model.core.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import uk.co.boots.columbus.cmdb.model.core.rest.support.CsvMessageConverter;

@Configuration
public class MessageConverterConfig extends WebMvcConfigurerAdapter{
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ByteArrayHttpMessageConverter byteConverter = new ByteArrayHttpMessageConverter();
        converters.add(byteConverter);        
    	converters.add(new CsvMessageConverter());
        super.configureMessageConverters(converters);
    }
}
