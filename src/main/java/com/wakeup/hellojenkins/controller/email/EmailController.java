package com.wakeup.hellojenkins.controller.email;

import com.wakeup.hellojenkins.pojo.Email;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="email") //  tags：你可以当作是这个组的名字。
@RestController
@RequestMapping("/email")
public class EmailController {

  @PostMapping("/sendEmail")
  @Operation(summary ="用户测试")
  public String register(@Validated Email email) {
//    MailAccount account = new MailAccount();
//    account.setHost("smtp.exmail.qq.com");
//    account.setPort(465);
//    account.setAuth(true);
//    account.setFrom("wangfangjie@zwcad.com");
//    account.setUser("wangfangjie@zwcad.com");
//    account.setPass("Zwsoft.202303");
//    MailUtil.send(account,email.getUsername(), email.getTitle(), email.getContent(), false);
//    MailUtil.send(account,"liyong@zwcad.com", "测试", "邮件来自wk-mail的测试", false);
//    String send = MailUtil.send(email.getUsername(), email.getTitle(), email.getContent(), false);
//    MailUtil.send("liyong@zwcad.com", "测试", "邮件来自wk-mail的测试", false);

    return "success";

  }
}
