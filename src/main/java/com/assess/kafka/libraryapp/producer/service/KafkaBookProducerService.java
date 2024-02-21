package com.assess.kafka.libraryapp.producer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.assess.kafka.libraryapp.model.Book;

@Service
public class KafkaBookProducerService {
    private KafkaTemplate<String, Book> kafkaTemplate;

    
    public Book sendBook(String topic, Book book) {
        return null;
    }
}
