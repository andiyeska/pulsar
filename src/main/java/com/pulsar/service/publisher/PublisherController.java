package com.pulsar.service.publisher;

import com.pulsar.service.model.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PublisherController {

  public static final String TOPIC = "pulsar.topic.testing";

  @Autowired
  private PulsarTemplate<Message> pulsarTemplate;

  @PostMapping(value = "/publish")
  private String publish(@RequestBody PublisherRequest request) throws PulsarClientException {
    int id = 1;
    int repetition = 1;

    for (int i = 1; i <= request.getTotalMessage(); i++) {
      if(repetition > request.getRepetition()) {
        id = i;
        repetition = 1;
      }

      Message message = Message.builder().id(id).build();
      pulsarTemplate.newMessage(message)
          .withTopic(TOPIC)
          .withMessageCustomizer(messageBuilder -> messageBuilder.key(String.valueOf(message.getId())))
          .send();
      ++repetition;
    }

    return "Success";
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  private static class PublisherRequest {
    private int totalMessage;
    private int repetition;
  }

}
