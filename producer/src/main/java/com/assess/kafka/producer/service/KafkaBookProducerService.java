package com.assess.kafka.producer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.assess.kafka.producer.model.Book;

@Service
public class KafkaBookProducerService {
    private final KafkaTemplate<String, Book> kafkaTemplate;

    
    public void sendBook(String topic, Book book) {
        
    }
}
