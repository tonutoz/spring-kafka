package org.example.springkafka.message;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageDto {

  private String id;

  private LocalDateTime createdOn;

  private LocalDateTime modifiedOn;

  private MessageStatus status;

  private String content;

  @Builder
  public MessageDto(String id, LocalDateTime createdOn, LocalDateTime modifiedOn,
      MessageStatus status, String content) {
    this.id = id;
    this.createdOn = createdOn;
    this.modifiedOn = modifiedOn;
    this.status = status;
    this.content = content;
  }


}
