package com.taco_cloud.messaging.jms;

import com.taco_cloud.domain.TacoOrder;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsMessagingService implements JmsMessaging{
    JmsTemplate jmsTemplate;

    @Autowired
    public JmsMessagingService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void SendOrder(TacoOrder tacoOrder) {
        jmsTemplate.convertAndSend(tacoOrder, this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE","WEB");
        return message;
    }
}
