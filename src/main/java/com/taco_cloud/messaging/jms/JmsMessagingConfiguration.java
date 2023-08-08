package com.taco_cloud.messaging.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.taco_cloud.domain.TacoOrder;
import jakarta.jms.Destination;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsMessagingConfiguration {

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter =
                new MappingJackson2MessageConverter();
//        messageConverter.setTypeIdPropertyName("_typeId");
//
//        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
//        typeIdMappings.put("order", TacoOrder.class);

        ObjectMapper mapper = JsonMapper.builder()
                .findAndAddModules()
                .build();

//        messageConverter.setTypeIdMappings(typeIdMappings);
        messageConverter.setObjectMapper(mapper);


        return messageConverter;
    }

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("tacocloud.order.queue");
    }
}
