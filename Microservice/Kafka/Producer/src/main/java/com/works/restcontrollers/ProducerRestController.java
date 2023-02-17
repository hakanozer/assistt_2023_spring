package com.works.restcontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.props.MessageData;
import com.works.services.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerRestController {

    final KafkaService kafkaService;
    final ObjectMapper objectMapper;

    @GetMapping("/send")
    public String send(@RequestBody MessageData messageData) {

        Runnable rn = () -> {
            String sendData = null;
            try {
                sendData = objectMapper.writeValueAsString(messageData);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100000; i++) {
                kafkaService.sendMessage(sendData);
            }
        };
        new Thread(rn).start();

        return "Message Send...";
    }

}
