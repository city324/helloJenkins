package com.wakeup.hellojenkins.controller.email;

import com.wakeup.hellojenkins.pojo.Email;
import com.wakeup.hellojenkins.pojo.EmailParam;
import com.wakeup.hellojenkins.util.EmailUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name ="httpEmail") //  tags：你可以当作是这个组的名字。
@RestController
@RequestMapping("/httpEmail")
public class HttpEmailController {

 /* @PostMapping("/sendEmail")
  @Operation(summary ="用户测试")
  public String register(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("from") String from, @RequestParam("userName[]") List<String> userName, @RequestPart MultipartFile[] fileList) {
    try {
      Email email = new Email(title, content, from, userName, fileList);
      EmailUtil.sendEmail(email);
    } catch (Exception e) {
      e.printStackTrace();
      return e.getMessage();
    }
    return "success";
  }*/

  @PostMapping("/body")
  @Operation(summary ="用户测试")
  public String register(EmailParam emailParam, @RequestParam("fileList") MultipartFile[] fileList) {
    try {
      Email email = new Email();
      BeanUtils.copyProperties(emailParam, email);
      email.setFiles(EmailUtil.transToFiles(fileList));
      String response = EmailUtil.sendEmail(email);
      System.out.println(response);
    } catch (Exception e) {
      e.printStackTrace();
      return e.getMessage();
    }
    return "success";
  }
}
