package com.wakeup.hellojenkins.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.wakeup.hellojenkins.pojo.Email;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Data
public class EmailUtil implements ApplicationContextAware {

  private static EmailProperties properties;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    EmailProperties emailProperties = applicationContext.getBean(EmailProperties.class);
    initProperties(emailProperties);
  }

  private static void initProperties(EmailProperties emailProperties) {
    properties = emailProperties;
    properties.setDomain("@" + properties.getDomain());
  }

  public static String sendEmail(Email email) {

    HashMap<String, Object> paramMap = new HashMap<>();
//    paramMap.put("url", properties.getUrl());
    paramMap.put("apiUser", properties.getApiUser());
    paramMap.put("apiKey", properties.getApiKey());
    paramMap.put("from", email.getFrom() + properties.getDomain());
    paramMap.put("fromName", email.getFrom());
//    paramMap.put("subject", email.getTitle());
    paramMap.put("html", email.getContent());
//    paramMap.put("attachments", email.getFiles());
    for (int i = 0; i < email.getFiles().size(); i++) {
      paramMap.put(String.format("attachments[%s]",i), email.getFiles().get(i));
    }
    List<String> username = email.getUsername();
    String to = username.stream().collect(Collectors.joining(";"));
    paramMap.put("to", to);
    HttpResponse response = HttpRequest.post(properties.getUrl()).form(paramMap).execute();
    return response.body();
  }

  public static  List<File> transToFiles(MultipartFile[] multipartFiles) {
    List<File> fileList = new ArrayList<>();
    for (int i = 0; i < multipartFiles.length; i++) {
      try {
        File file = new File(multipartFiles[i].getOriginalFilename());
        FileUtil.writeBytes(multipartFiles[i].getBytes(), file);
        fileList.add(file);
      } catch (Exception e) {
        e.printStackTrace();
        // todo
        continue;
      }
    }
    return fileList;
  }
}
