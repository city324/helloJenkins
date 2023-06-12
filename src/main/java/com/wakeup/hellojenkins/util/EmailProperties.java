package com.wakeup.hellojenkins.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "sendcloud")
public class EmailProperties {

  private String url;
  private String apiUser;
  private String apiKey;
  private String domain;


}
