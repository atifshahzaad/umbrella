package com.ou.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class OuAccountConfig {

	@Value("${ou_account.topic.userCreated}")
	private String userCreatedTopic;

	@Value("${ou_account.topic.userNameUpdated}")
	private String userNameUpdatedTopic;

	@Value("${ou_account.topic.userRoleUpdated}")
	private String userRoleUpdatedTopic;

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

	@Bean
	public NewTopic topicUserCreated() {
		return TopicBuilder.name(userCreatedTopic).partitions(3).replicas(3).build();
	}

	@Bean
	public NewTopic topicUserNameUpdated() {
		return TopicBuilder.name(userNameUpdatedTopic).partitions(3).replicas(3).build();
	}

	@Bean
	public NewTopic topicUserRoleUpdated() {
		return TopicBuilder.name(userRoleUpdatedTopic).partitions(3).replicas(3).build();
	}

}
