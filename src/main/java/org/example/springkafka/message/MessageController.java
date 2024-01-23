package org.example.springkafka.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/kafka")
public class MessageController {

  private final MessageService service;
  @GetMapping("/consumer")
  public String doConsumer() {
      log.info("zzzzzzzzzzzzzzzzzz");
      return service.pollingKafkamessage();

  }


}
