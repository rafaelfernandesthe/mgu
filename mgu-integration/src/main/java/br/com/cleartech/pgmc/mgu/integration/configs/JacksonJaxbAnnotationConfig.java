package br.com.cleartech.pgmc.mgu.integration.configs;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

@Configuration
public class JacksonJaxbAnnotationConfig extends WebMvcConfigurationSupport {

	// @Bean
	// public MappingJackson2HttpMessageConverter
	// customJackson2HttpMessageConverter() {
	// MappingJackson2HttpMessageConverter converter = new
	// MappingJackson2HttpMessageConverter();
	// ObjectMapper objectMapper = new XmlMapper();
	// objectMapper.registerModule( new JaxbAnnotationModule() );
	// // objectMapper.setSerializationInclusion( Include.NON_NULL );
	//
	// // objectMapper.configure(
	// // DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false );
	// converter.setObjectMapper( objectMapper );
	// return converter;
	// }

	@Override
	public void configureMessageConverters( List<HttpMessageConverter<?>> converters ) {
		Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.xml();
		builder.indentOutput( true );
		ObjectMapper objectMapper = builder.build();
		objectMapper.registerModule( new JaxbAnnotationModule() );
		// objectMapper.setSerializationInclusion( Include.NON_NULL );
		objectMapper.setSerializationInclusion( Include.NON_EMPTY );

		converters.add( new MappingJackson2XmlHttpMessageConverter( objectMapper ) );
		// converters.add( customJackson2HttpMessageConverter() );
		super.addDefaultHttpMessageConverters( converters );
	}
}
