package com.ou.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class OuHRMSConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	 @Bean
	 public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
	     return builder -> {
	         DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	         builder.serializerByType(LocalDate.class, new LocalDateSerializer(localDateFormatter));
	         builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(localDateFormatter));

	         DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	         builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(localDateTimeFormatter));
	         builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(localDateTimeFormatter));
	     };
	 }
}
