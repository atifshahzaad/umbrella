package com.ou.event.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ou.event.Event;

@Service
public class EventProducer {

	@Autowired
	private KafkaTemplate<String, Event> kafkaTemplate;

	public void send(String topic, Event event) {
		kafkaTemplate.send(topic, event);
	}
	
	
}
