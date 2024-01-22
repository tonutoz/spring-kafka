package org.example.springkafka.message.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.example.springkafka.message.MessageDto;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class MessageSerializer implements Serializer<MessageDto> {
  private final ObjectMapper objectMapper = new ObjectMapper();
  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    Serializer.super.configure(configs, isKey);
  }

  @Override
  public byte[] serialize(String s, MessageDto data) {
    try {
      if (data == null){
        log.info("Null received at serializing");
        return null;
      }
      log.info("Serializing...");
      return objectMapper.writeValueAsBytes(data);
    } catch (Exception e) {
      throw new SerializationException("Error when serializing MessageDto to byte[]");
    }
  }

  @Override
  public byte[] serialize(String topic, Headers headers, MessageDto data) {
    return Serializer.super.serialize(topic, headers, data);
  }

  @Override
  public void close() {
    Serializer.super.close();
  }
}
