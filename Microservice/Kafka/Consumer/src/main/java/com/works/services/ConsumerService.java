package com.works.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.props.MessageData;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    final ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen( String message ) {
        MessageData messageData = null;
        try {
            messageData = objectMapper.readValue(message, MessageData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println( "Pull Data : " +  messageData );
    }

}
