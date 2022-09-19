package com.wakeup.hellojenkins;

import com.wakeup.hellojenkins.mapper.UserMapper;
import com.wakeup.hellojenkins.pojo.User;
import com.wakeup.hellojenkins.util.SequnceUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
@Slf4j
public class RedissionTest {

    @Resource
    SequnceUtil sequnceUtil;

    @Test
    public void testSelect() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(60);
        String key = "123";
        for (int i = 0; i < 60; i++) { //开50个线程
            MyTread thread = new MyTread(sequnceUtil, "testTread ->" + i, key,latch);
            thread.start();
        }
        System.out.println("=================== >await");
        latch.await();
        System.out.println("=================== >over");
    }

/*    @Test
    public void testSelect2() throws InterruptedException {
        for (int i = 1; i < 60; i++) { //开50个线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sequnceUtil.function();
                }
            }, "testTread ->" + i).start();
        }
        Thread.sleep(100000);
    }*/


    class MyTread extends Thread {
        private SequnceUtil sequnceUtil;
        private String key;
        private CountDownLatch latch;

        public MyTread(SequnceUtil sequnceUtil, String name, String key, CountDownLatch latch) {
            super(name);
            this.sequnceUtil = sequnceUtil;
            this.key = key;
            this.latch = latch;
        }

        @Override
        public void run() {
            sequnceUtil.function(key);
            latch.countDown();
        }
    }


}
