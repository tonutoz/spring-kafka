package org.example.springkafka.message.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.springkafka.message.MessageDto;
import org.springframework.stereotype.Component;
import org.apache.kafka.common.errors.SerializationException;
@Component
@Slf4j
public class MessageDeserializer implements Deserializer<MessageDto> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
  }

  @Override
  public MessageDto deserialize(String topic, byte[] data) {
    try {
      if (data == null){
        log.info("Null received at deserializing");
        return null;
      }
      log.info("Deserializing...");
      return objectMapper.readValue(new String(data, "UTF-8"), MessageDto.class);
    } catch (Exception e) {
      throw new SerializationException("Error when deserializing byte[] to MessageDto");
    }
  }

  @Override
  public void close() {
  }
}
