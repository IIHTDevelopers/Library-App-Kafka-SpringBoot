package com.assess.kafka.libraryapp.producer.config;

import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import com.assess.kafka.libraryapp.model.Book;

public class KafkaProducerConfig {

	private String bootStrapServer;
	
	public ProducerFactory<String,Book> producerFactory(){
		return null;
	}
	
	public KafkaTemplate<String, Book> kafkaTemplate(){
		return null;
	}
}
