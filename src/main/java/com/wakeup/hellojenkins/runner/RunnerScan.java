package com.wakeup.hellojenkins.runner;

import com.wakeup.hellojenkins.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RunnerScan implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
/*        AnnotationConfigServletWebServerApplicationContext applicationContext = (AnnotationConfigServletWebServerApplicationContext) SpringUtil.getApplicationContext();
        applicationContext.scan("E:/temp/com/lib/");
        applicationContext.refresh();
        Object third = applicationContext.getBean("Third");
        System.out.println(third.toString());*/
    }
}
