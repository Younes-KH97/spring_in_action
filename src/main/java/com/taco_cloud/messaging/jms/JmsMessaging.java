package com.taco_cloud.messaging.jms;

import com.taco_cloud.domain.TacoOrder;

public interface JmsMessaging {
   void SendOrder(TacoOrder tacoOrder);
}
