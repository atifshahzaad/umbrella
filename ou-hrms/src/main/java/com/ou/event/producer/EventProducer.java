package com.ou.event.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ou.event.HrmsEvent;


@Service
public class EventProducer {

	@Autowired
	private KafkaTemplate<String, HrmsEvent> kafkaTemplate;

	public void send(String topic, HrmsEvent event) {
		kafkaTemplate.send(topic, event);
	}
}
