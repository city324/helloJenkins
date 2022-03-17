package com.wakeup.hellojenkins.controller;

import com.wakeup.hellojenkins.listener.MyEvent;
import com.wakeup.hellojenkins.util.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String index(String name) {

        return "你好：" + name;
    }

    @GetMapping("/testListen")
//    @EventListener(classes={MyEvent.class})
    public String testListen() {
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
        applicationContext.publishEvent(new MyEvent(applicationContext));
        return "testListen========================";
    }

}
