package com.example.demo.push;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public interface Push {
    
    void dowork(WebSocketSession curSession, TextMessage msg);
}