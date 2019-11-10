package com.example.demo.config;

import com.example.demo.cache.MemoryPushCache;
import com.example.demo.cache.PushCache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSocket
@Slf4j
public class WebSocketConfig implements WebSocketConfigurer {

    @Bean
    ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    @Bean
    SocketMessageHandler socketMessageHandler(){
        return new SocketMessageHandler();
    }
    @Bean
    HttpHandshakeInterceptor httpHandshakeInterceptor(){
        return new HttpHandshakeInterceptor();
    }
    @Bean
    HttpHandshakeHandler httpHandshakHandler(){
        return new HttpHandshakeHandler();
    }
    @Bean
    PushCache pushCache(){
        return new MemoryPushCache();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketMessageHandler(), "/ws")
        .addInterceptors(httpHandshakeInterceptor())
        .setHandshakeHandler(httpHandshakHandler())
        .setAllowedOrigins("*");
    }
    
    
}