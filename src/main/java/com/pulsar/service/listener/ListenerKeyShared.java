package com.pulsar.service.listener;

import com.pulsar.service.model.Message;
import com.pulsar.service.publisher.PublisherController;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ListenerKeyShared {

  private static final String SUBSCRIPTION_NAME = "testing-key_shared";

  @PulsarListener(
      subscriptionName = SUBSCRIPTION_NAME,
      subscriptionType = SubscriptionType.Key_Shared,
      topics = PublisherController.TOPIC,
      batch = true
  )
  public void listen(List<Message> messages) {
    log.info("Listening to message with id: {} in subscription: {}", messages, SUBSCRIPTION_NAME);
  }

}
