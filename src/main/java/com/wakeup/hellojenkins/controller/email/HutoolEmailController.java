package com.wakeup.hellojenkins.controller.email;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.wakeup.hellojenkins.pojo.Email;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="sendCloud") //  tags：你可以当作是这个组的名字。
@RestController
@RequestMapping("/huttol")
public class HutoolEmailController {

  @PostMapping("/sendEmail")
  @Operation(summary ="用户测试")
  public String register(@Validated Email email) {

    final String url = "https://api.sendcloud.net/apiv2/mail/send";
    final String apiUser = "WuKong_Email_ApiUser";
    final String apiKey = "a4e8d4f9611ae57d1b04951e4d1261ee";
    email.setContent("<!DOCTYPE html><html><head>    <meta charset=\"utf-8\">    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">    <meta name=\"descr                                          iption\" content=\"email code\">    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"></head><!--邮箱验证码模板--><body><div style=\"                                          background-color:#ECECEC; padding: 35px;\">    <table cellpadding=\"0\" align=\"center\"           style=\"width: 800px;height: 100%; margin: 0px auto; t                                          ext-align: left; position: relative; border-radius: 5px;font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 15                                          3) 0px 0px 5px; border-collapse: collapse; background: #fff initial initial initial initial;\">        <tbody>        <tr>            <th valign=\"mi                                          ddle\"                style=\"height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-botto                                          m-color: #0389E6; background-color: #0389E6; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bot                                          tom-left-radius: 0px;\">                <span style=\"color: rgb(255, 255, 255);  font-family: 微软雅黑; font-size: large; \">【悟空】邀请您加入租户进                                          行协作</span>            </th>        </tr>        <tr>            <td style=\"word-break:break-all\">                <div style=\"padding:25px 35px 4                                          0px; background-color:#fff;opacity:0.8;\">                    <h2 style=\"margin: 5px 0px; \">                        <span style=\"line-height: 20px;                                            color: #333333; \">                            <span style=\"line-height: 22px;  font-size: medium; \">                                尊敬的用户：</                                          span>                        </span>                    </h2>                    <!-- 中文 -->                    <p>您好！<span style=\"color: #ff8                                          c00; \">测试修改内容</span> 邀请您加入租户：<span                            style=\"color: #ff8c00; \">测试修改内容zwsoft</span></p><br>                    <p>点击下方                                          链接登录/注册账号，并接受邀请</p>                    <a href=http://192.168.50.196:8011/manage>点击跳转</a>                    <div style=\"width:10                                          0%;margin:0 auto;\">                        <div                            style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margi                                          n-bottom:20px;line-height:1.3em;font-size:12px;\">                            <p>悟空团队</p>                            <p>联系我们：wukong@zwsoft.                                          com</p>                            <br>                            <p>此为系统邮件，请勿回复<br>                                Please do not reply                                           to this system email                            </p>                        </div>                    </div>                </div>            </td                                          >        </tr>        </tbody>    </table></div></body></html>");
    HashMap<String, Object> paramMap = new HashMap<>();
    paramMap.put("apiUser", apiUser);
    paramMap.put("apiKey", apiKey);
    paramMap.put("from", "shemin@zwcad.com");
    paramMap.put("fromName", "123");
    paramMap.put("to", email.getUsername().stream().collect(Collectors.joining(";")));
//    paramMap.put("subject", email.getTitle());
    paramMap.put("html", email.getContent());

    try {
      HttpResponse execute = HttpRequest.post(url).form(paramMap).execute();
      System.out.println(execute.body());
      return execute.body();
    } catch (Exception e) {
      return "error";
    }
  }
}
