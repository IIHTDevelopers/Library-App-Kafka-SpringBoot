package com.assess.kafka.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.assess.kafka.consumer.model.Book;

@Service
public class KafkaBookConsumerService {

    
    public void listenAddBook(Book book) {
        // Process the received book
       
    }
    
    
    public void listenUpdateBook(Book book) {
        // Process the received book
        
    }
}
