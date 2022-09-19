package com.wakeup.hellojenkins.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
//@Component
public class SequnceUtil {

    @Autowired
    RedissonClient redissonClient;

    int n = 500;

    private static final String kee = "kee";

    public void function(String key) {
        //定义锁
        RLock lock = redissonClient.getLock(key);
//        lock.lock();
        try {
            //尝试加锁,最大等待时间300毫秒，上锁30毫秒自动解锁
            if (lock.tryLock(10, 10, TimeUnit.SECONDS)) {
                log.info("线程:" + Thread.currentThread().getName() + "获得了锁");
                log.info("剩余数量:{}", --n);
            }
        } catch (Exception e) {
            log.error("程序执行异常:{}", e);
        } finally {
            log.info("线程:" + Thread.currentThread().getName() + "准备释放锁");
            //释放锁
            lock.unlock();
        }
    }
    //加法

}
