package com.wakeup.hellojenkins.pojo;

import com.mysql.cj.protocol.Protocol;
import com.wakeup.hellojenkins.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
//@Scope("prototype")
public class Human {
    public Human() {
        System.out.println("构造human" + this);
        this.user = (User) SpringUtil.getApplicationContext().getBean("user");
        System.out.println(user);
    }

    //    @Autowired
    public User user;

//    @PostConstruct
//    private void initUser() {
//        this.user = (User) SpringUtil.getApplicationContext().getBean("user");
//        System.out.println(user);
//    }
}
