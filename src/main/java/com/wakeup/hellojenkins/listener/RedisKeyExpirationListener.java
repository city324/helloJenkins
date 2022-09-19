package com.wakeup.hellojenkins.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.crypto.Data;
import java.util.Date;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    private final static Logger logger = LoggerFactory.getLogger(RedisKeyExpirationListener.class);

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String expiredKey = message.toString();//获取所有key
            System.out.print(expiredKey+"刚刚过期了："+ new Date());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}