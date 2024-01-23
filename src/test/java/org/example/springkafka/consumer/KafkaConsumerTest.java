package org.example.springkafka.consumer;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import org.example.springkafka.KafkaConsumer;
import org.example.springkafka.KafkaProducer;
import org.example.springkafka.message.MessageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(partitions = 1,
    brokerProperties = {"listeners=PLAINTEXT://localhost:9092"},
    ports = { 9092 }
)
public class KafkaConsumerTest {

  @Autowired
  private KafkaConsumer consumer;

  @Autowired
  private KafkaProducer producer;

  @Test
  public void giveEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
      throws Exception {

    String topic = "tonutoz-topic";

    int testCnt = 10000;

    IntStream.rangeClosed(0,testCnt).forEach(i->{
      MessageDto payload = MessageDto.builder()
          .createdOn(LocalDateTime.now())
          .content("content"+i)
          .build();
      producer.send(topic, payload);
    });

    // 모든 메시지를 수신할 때까지 기다립니다.
    consumer.getLatch().await(10, TimeUnit.SECONDS);

    System.out.println("============================================================");
    System.out.println(consumer.getPayloads().size());
    System.out.println(consumer.getPayloads());


  }


}
