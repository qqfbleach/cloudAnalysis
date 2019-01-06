package com.qqfall.cloud.apiKey.dao.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "apikey")
public class ApiKeyEntity {

  @Indexed
  private String id;

  private String userId;

  private String secretKey;

  private HostType type;
}
