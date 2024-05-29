package com.pulsar.service.listener;

import com.pulsar.service.model.Message;
import com.pulsar.service.publisher.PublisherController;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ListenerShared {

  private static final String SUBSCRIPTION_NAME = "testing-shared";

  @PulsarListener(
      subscriptionName = SUBSCRIPTION_NAME,
      subscriptionType = SubscriptionType.Shared,
      topics = PublisherController.TOPIC
  )
  public void listen(Message message) {
    log.info("Listening to message with id: {} in subscription: {}", message.getId(), SUBSCRIPTION_NAME);
  }

}
