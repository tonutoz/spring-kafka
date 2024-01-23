package org.example.springkafka;

import java.time.LocalDateTime;
import java.util.stream.IntStream;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.springkafka.message.MessageDto;
import org.example.springkafka.message.MessageStatus;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDataRunner implements ApplicationRunner {

  private final KafkaProducer producer;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    final int testCnt = 100;
    final String topic = "baeldung";
    IntStream.range(0,testCnt).forEach(i->{
      MessageDto payload = MessageDto.builder()
          .createdOn(LocalDateTime.now())
          .content("content"+i)
          .status(MessageStatus.WRITE)
          .build();
      producer.send(topic, payload);
    });


  }
}
