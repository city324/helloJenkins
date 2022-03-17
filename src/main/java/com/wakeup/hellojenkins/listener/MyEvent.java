package com.wakeup.hellojenkins.listener;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class MyEvent extends ApplicationEvent {


    public MyEvent(Object source) {
        super(source);
        say();
    }

    public MyEvent(Object source, Clock clock) {
        super(source, clock);
    }

    public void say(){
        System.out.println("事件生成了");
    }
}
