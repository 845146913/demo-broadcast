package com.example.demo.push;

import java.io.IOException;
import java.util.Collection;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

/**
 * MessageSender
 */
@Component
@Slf4j
public class MessageSender {

    public void unicast(WebSocketSession session, TextMessage msg) {
        try {
            if(session.isOpen()){
                session.sendMessage(msg);
            }
        } catch (IOException e) {
            log.error("\r\n >>>>>>>> MessageSender unicast failed!  e:{}", e.getMessage());
        }
    }

    public void broadcast(Collection<WebSocketSession> queue, TextMessage msg){
        if(CollectionUtils.isEmpty(queue)){
            return;
        }
        queue.forEach(session -> {
            if(session.isOpen()){
                try {
                    session.sendMessage(msg);
                } catch (IOException e) {
                    log.error("\r\n >>>>>>>> MessageSender broadcast failed! session.{},  e:{}", 
                    session, e.getMessage());
                }
            }
        });
    }
    
}