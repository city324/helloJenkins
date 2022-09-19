package com.wakeup.hellojenkins.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data

public class RegisterVO {




    @Length(min = 4, max = 20, message = "用户名长度为4-20字符")
    @NotBlank(message = "用户名不能为空")
    private String username;


    @Length(min = 4, max = 20, message = "用户名长度为4-20字符")
    @Pattern(regexp = "^1[3-9][0-9]{9}$", message = "手机号格式不合法")
    private String phone;



    @NotNull(message = "验证码不能为空")
    @Pattern(regexp = "^[0-9]{6}$", message = "验证码为6位数字")
    private String code;


    @NotBlank(message = "密码不能为空")
    @Length(min = 8, max = 16, message = "密码长度为8-16字符")
    private String password;


}