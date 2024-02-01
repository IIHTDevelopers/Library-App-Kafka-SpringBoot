package com.assess.kafka.producer.config;

import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;


public class ProducerConfig {

	private String bootStrapServer;
	
	public ProducerFactory<String,String> producerFactory(){
		return null;
	}
	
	public KafkaTemplate<String, String> kafkaTemplate(){
		return null;
	}
}
