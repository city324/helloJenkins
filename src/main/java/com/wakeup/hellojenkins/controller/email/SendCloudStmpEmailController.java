package com.wakeup.hellojenkins.controller.email;

import com.sun.mail.smtp.SMTPTransport;
import com.wakeup.hellojenkins.pojo.Email;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.File;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name ="smtp") //  tags：你可以当作是这个组的名字。
@RestController
@RequestMapping("/smtp")
public class SendCloudStmpEmailController {

  @PostMapping("/sendEmailSmtp")
  @Operation(summary ="用户测试")
  public String register(@Validated Email email) {
    try {

      // configure javamail
      Properties props = System.getProperties();
      props.setProperty("mail.transport.protocol", "smtp");
      props.put("mail.smtp.host", "smtp.sendcloud.net");
      props.put("mail.smtp.port", 25);
      props.setProperty("mail.smtp.auth", "true");
      props.put("mail.smtp.connectiontimeout", 180);
      props.put("mail.smtp.timeout", 600);
      props.setProperty("mail.mime.encodefilename", "true");

      // Verification using api_user and api_key
//      final String apiUser = "oGQ2nju07yP-40DSpkT5Cj6xW0t4_test_EaESKb";
//      final String apiKey = "075947e761c8a3e97f66f197b1f09be3";
      final String apiUser = "WuKong_Email_ApiUser";
      final String apiKey = "a4e8d4f9611ae57d1b04951e4d1261ee";

      String to = email.getUsername().stream().collect(Collectors.joining(";"));

      Session mailSession = Session.getInstance(props, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(apiUser, apiKey);
        }
      });

      SMTPTransport transport = (SMTPTransport) mailSession.getTransport("smtp");

      MimeMessage message = new MimeMessage(mailSession);
      // The from, use the correct email address instead
      message.setFrom(new InternetAddress("fromZMS@accounts.zwsoft.com", "中台邮件账号", "UTF-8"));
      // The recipient address,use the correct email address instead
      message.addRecipient(RecipientType.TO, new InternetAddress(to));
      // Mail theme
//      message.setSubject(email.getTitle(), "UTF-8");

      Multipart multipart = new MimeMultipart("alternative");

      // Add HTML message body
      String html = "<html><head></head><body>"
          + "<p>Welcome to<a href='https://api.sendcloud.net'>SendCloud!</a></p>"
          + "</body></html> ";
      BodyPart contentPart = new MimeBodyPart();
      contentPart.setHeader("Content-Type", "text/html;charset=UTF-8");
      contentPart.setHeader("Content-Transfer-Encoding", "base64");
      contentPart.setContent(html, "text/html;charset=UTF-8");
      multipart.addBodyPart(contentPart);

      // Add attachment ( SMTP cannot use FileStream )
      File file = new File("E:\\temp\\202301\\search.txt");
      BodyPart attachmentBodyPart = new MimeBodyPart();
      DataSource source = new FileDataSource(file);
      attachmentBodyPart.setDataHandler(new DataHandler(source));
      attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
      multipart.addBodyPart(attachmentBodyPart);
      message.setContent(multipart);

      // Connect to sendcloud server and send mail
      transport.connect();
      transport.sendMessage(message, message.getRecipients(RecipientType.TO));

      String messageId = getMessage(transport.getLastServerResponse());
      String emailId = messageId + "0$" + to;
      System.out.println("messageId:" + messageId);
      System.out.println("emailId:" + emailId);
      transport.close();
      return "success";
    } catch (Exception e) {
      e.printStackTrace();
      return e.getMessage();
    }
  }

  private static String getMessage(String reply) {
    String[] arr = reply.split("#");

    String messageId = null;

    if (arr[0].equalsIgnoreCase("250 ")) {
      messageId = arr[1];
    }

    return messageId;
  }
}
