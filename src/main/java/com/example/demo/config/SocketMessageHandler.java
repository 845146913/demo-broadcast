package com.example.demo.config;

import javax.annotation.Resource;

import com.example.demo.push.MountPush;
import com.example.demo.push.SessionCache;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocketMessageHandler extends TextWebSocketHandler {
    @Resource
    MountPush mountPush;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        log.info("\r\n 关闭连接：{}， status:{}", session.getPrincipal(), status);
        SessionCache.removeUser(session);
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("\r\n 建立连接：{}", session.getPrincipal());
        SessionCache.putUser(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("\r\n 消息：{}， message:{}", session.getPrincipal(), message);
        mountPush.dowork(session, message);
    }
    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
        log.info("\r\n 连接异常：{}, e:{}", session.getPrincipal(), e);
        super.handleTransportError(session, e);
    }
    
}