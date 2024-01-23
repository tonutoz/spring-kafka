package org.example.springkafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springkafka.message.MessageDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
  private final KafkaTemplate<String, MessageDto> kafkaTemplate;

  public void send(String topic, MessageDto payload) {
    log.info("sending payloa={} to topic={}", payload, topic);
    kafkaTemplate.send(topic, payload);
  }
}
