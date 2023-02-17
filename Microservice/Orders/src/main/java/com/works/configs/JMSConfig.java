package com.works.configs;

import lombok.RequiredArgsConstructor;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

@Configuration
@RequiredArgsConstructor
public class JMSConfig {

    @Value("${jms.broker.url}")
    private String brokerUrl;

    @Value("${jms.queue.name}")
    private String queueName;


    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(new ActiveMQConnectionFactory(brokerUrl));
    }

    @Bean
    public Destination destination() {
        return new ActiveMQQueue(queueName);
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory, Destination destination) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestination(destination);
        return jmsTemplate;
    }



}