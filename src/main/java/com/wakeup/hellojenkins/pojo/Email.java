package com.wakeup.hellojenkins.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.File;
import java.util.List;
import lombok.Data;

@Data

public class Email {

  private List<String> username;

  @Schema(name = "title111",title = "标题",description = "邮件标题",nullable = true,maxLength = 5)
  private String title_adsa;

  private String from;

  private String content;

  @JsonIgnore
  private List<File> files;

  private List<String> emails;


/*  public Email(String title, String content, String from, List<String> userName, MultipartFile[] multipartFiles) {
    this.username = username;
    this.title = title;
    this.from = from;
    this.content = content;
    List<File> fileList = new ArrayList<>();
    for (int i = 0; i < multipartFiles.length; i++) {
      try {
        File file = new File(multipartFiles[i].getOriginalFilename());
        FileUtil.writeBytes(multipartFiles[i].getBytes(), file);
        fileList.add(file);
      } catch (Exception e) {
        // todo
        continue;
      }
    }
    this.fileList = fileList;
  }*/
}