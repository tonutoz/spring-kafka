package org.example.springkafka;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.springkafka.message.MessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Getter
public class KafkaConsumer {
  private CountDownLatch latch = new CountDownLatch(10);
  private final List<MessageDto> payloads = new ArrayList<>();
  private MessageDto payload;

  // record 를 수신하기 위한 consumer 설정
  @KafkaListener(topics = "baeldung",
      containerFactory = "filterListenerContainerFactory")
  public void receive(ConsumerRecord<String, MessageDto> consumerRecord) {
    payload = consumerRecord.value();
    log.info("received payload = {}", payload.toString());
    payloads.add(payload);
    latch.countDown();
  }

  public void resetLatch() {
    latch = new CountDownLatch(1);
  }
}
