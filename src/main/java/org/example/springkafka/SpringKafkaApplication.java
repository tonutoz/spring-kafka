package org.example.springkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringKafkaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringKafkaApplication.class, args);
  }

}
