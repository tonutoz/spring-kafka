package org.example.springkafka.message;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.example.springkafka.KafkaConsumer;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageService {

  private final Deserializer<MessageDto> messageDeserializer;

  private final Serializer<MessageDto> messageSerializer;

  private final KafkaConsumer consumer;

  private final MessageRepository repository;

  @Transactional
  public String pollingKafkamessage() {

    List<MessageDto> pollingList =  consumer.getPayloads();

    log.info("polling Size {}", pollingList.size());

    List<Message> insertList =  pollingList.stream().map(dto -> {
      log.info(dto.getStatus().name());
      return Message.builder()
          .content(dto.getContent())
          .status(MessageStatus.READ)
          .build();
    }).toList();

    repository.saveAll(insertList);

    return "success";
  }


}
