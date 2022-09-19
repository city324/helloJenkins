package com.wakeup.hellojenkins.controller;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Slf4j
@ServerEndpoint("/ws_download")
@Component
public class DownloadServerEndpoint {
    private String url = "https://mirrors.aliyun.com/ubuntu-releases/trusty/ubuntu-14.04.6-server-amd64.iso";
    private String option = "-c -O I:/ubuntu-14.04.6-server-amd64.iso";

    @OnOpen
    public void onOpen(Session session) throws Exception {
        log.info("sessionId：{}", session.getId());
        WGetUtil.wgetProgressRation(option, url, progress -> sendText(session, progress));
    }

    private void sendText(Session session, WGetUtil.Progress progress) {
        try {
            session.getBasicRemote().sendText(progress.toJSONString());
        } catch (IOException e) {
            log.error("WebSocket发生消息失败！", e);
        }
    }

    @OnClose
    public void onClose() {
        log.info("WebSocket连接关闭");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.info("WebSocket错误", throwable);
    }
}

