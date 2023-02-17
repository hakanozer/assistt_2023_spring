package com.works.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

    final KafkaTemplate kafkaTemplate;

    @Value("${spring.kafka.producer.topic}")
    private String topicName;

    public void sendMessage( String message ) {
         kafkaTemplate.send(topicName, message);
    }

}
