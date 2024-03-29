package com.wakeup.hellojenkins.controller;

import com.wakeup.hellojenkins.listener.MyEvent;
import com.wakeup.hellojenkins.mapper.UserMapper;
import com.wakeup.hellojenkins.util.SpringUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @GetMapping("/testListen")
//    @EventListener(classes={MyEvent.class})
    public String testListen() {
        ApplicationContext applicationContext = SpringUtil.getApplicationContext();
//        Object bean = applicationContext.getBean("com.Third");
//        applicationContext.publishEvent(new MyEvent(applicationContext));
//        Object third = applicationContext.getBean("third");
//        System.out.println(bean.toString());
        applicationContext.publishEvent(new MyEvent("AA"));
        return "testListen========================";
    }

    @GetMapping("/timeout")
//    @EventListener(classes={MyEvent.class})
    public String timeout() {
        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "timeout========================";
    }

/*    @GetMapping("/saveBatch")
    public String saveBatch() {

        return "timeout========================";
    }*/

    @GetMapping("/docker/hello")
    public String helloDocker() {
        return "hello docker Alon";
    }
}
