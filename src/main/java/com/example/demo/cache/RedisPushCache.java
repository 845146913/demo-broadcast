package com.example.demo.cache;

import java.util.Map;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

public class RedisPushCache implements PushCache {

    @Override
    public void putUser(WebSocketSession session) {

    }

    @Override
    public void removeUser(WebSocketSession s) {

    }

    @Override
    public Set<WebSocketSession> getUsers() {
        return null;
    }

    @Override
    public Map<String, Set<WebSocketSession>> getRoomMap() {
        return null;
    }

    @Override
    public Set<WebSocketSession> getRoomMapById(String roomId) {
        return null;
    }

    @Override
    public void putRoom(String roomId, WebSocketSession s) {

    }

    @Override
    public void removeRoom(String roomId) {

    }

    @Override
    public boolean roomHasUser(String roomId, WebSocketSession s) {
        return false;
    }

    @Override
    public void removeRoomByUser(String roomId, WebSocketSession s) {

    }
    
}