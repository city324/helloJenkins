package com.wakeup.hellojenkins.controller;

import com.wakeup.hellojenkins.pojo.RegisterVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @PostMapping("/register")

    public String register(@Validated RegisterVO registerVO) {

        // 校验出错会被异常处理器处理

        return "success";

    }
}
