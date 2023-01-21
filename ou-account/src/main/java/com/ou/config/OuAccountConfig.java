package com.ou.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OuAccountConfig {

	@Value("${ou_account.topic.userCreated}")
	private String userCreatedTopic;
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	/*
	 * @Bean public NewTopic newTopic() { return TopicBuilder.name(userCreatedTopic)
	 * .partitions(3) .replicas(3) .build(); }
	 */
}
