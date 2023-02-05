package com.ou.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OUViewServiceConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}