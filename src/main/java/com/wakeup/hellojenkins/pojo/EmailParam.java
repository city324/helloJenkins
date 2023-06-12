package com.wakeup.hellojenkins.pojo;

import java.util.List;
import lombok.Data;

@Data

public class EmailParam {

  private List<String> username;

  private String title;

  private String from;

  private String content;
}