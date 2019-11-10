package com.example.demo.config;

import java.security.Principal;
import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
            Map<String, Object> attributes) {
        Object o = attributes.get("user");
        if(null != o){
            return new Principal(){
            
                @Override
                public String getName() {
                    return (String)o;
                }
            };
        }
        return super.determineUser(request, wsHandler, attributes);
    }

}