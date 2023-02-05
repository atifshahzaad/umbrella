package com.ou.event.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ou.model.Project;

@Service
public class EventProducer {

	@Value("${ou-pms.topic.projectCreated}")
	private String projectCreatedTopic;

	@Autowired
	private KafkaTemplate<String, Project> kafkaTemplate;

	public void sendProjectCreatedEvent(Project project) {
		kafkaTemplate.send(projectCreatedTopic, project);
	}

}
