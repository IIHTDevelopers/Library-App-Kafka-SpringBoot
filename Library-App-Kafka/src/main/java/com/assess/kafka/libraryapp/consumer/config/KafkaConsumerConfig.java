package com.assess.kafka.libraryapp.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.assess.kafka.libraryapp.model.Book;

@Configuration
public class KafkaConsumerConfig {

    
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, Book> consumerFactory() {
        return null;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Book> kafkaListenerContainerFactory() {
        return null;
    }
}
