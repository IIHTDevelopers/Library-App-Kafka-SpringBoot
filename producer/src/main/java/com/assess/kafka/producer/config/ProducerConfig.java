package com.assess.kafka.producer.config;

public class ProducerConfig {

	private String bootStrapServer;
	
	public ProducerFactory<String,String> producerFactory(){
		return null;
	}
	
	public KafkaTemplate<String, String> kafkaTemplate(){
		return null;
	}
}
