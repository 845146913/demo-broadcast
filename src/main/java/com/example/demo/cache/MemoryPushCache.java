package com.example.demo.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import com.example.demo.cache.PushCache;

import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.WebSocketSession;

public class MemoryPushCache implements PushCache {
    
    /**
     * 房间链接缓存
     */
    private final Map<String, Set<WebSocketSession>> ROOM_MAP = new ConcurrentHashMap<>();
    /**
     * 在线链接缓存
     */
    private final CopyOnWriteArraySet<WebSocketSession> USERS = new CopyOnWriteArraySet<>();

    public void putUser(WebSocketSession session) {
        USERS.add(session);
    }

    public void removeUser(WebSocketSession s) {
        USERS.remove(s);
    }

    public Set<WebSocketSession> getUsers() {
        return USERS;
    }

    public Map<String, Set<WebSocketSession>> getRoomMap() {
        return ROOM_MAP;
    }

    public Set<WebSocketSession> getRoomMapById(String roomId) {
        return ROOM_MAP.get(roomId);
    }

    public void putRoom(String roomId, WebSocketSession s) {
        Set<WebSocketSession> sessions = ROOM_MAP.get(roomId);
        if(CollectionUtils.isEmpty(sessions)){
            sessions = new CopyOnWriteArraySet<>();
        }
        sessions.add(s);
        ROOM_MAP.put(roomId, sessions);
    }
    public void removeRoom(String roomId){
        ROOM_MAP.remove(roomId);
    }
    public boolean roomHasUser(String roomId, WebSocketSession s){
        Set<WebSocketSession> sessions = getRoomMapById(roomId);
        if(CollectionUtils.isEmpty(sessions)){
            return false;
        }
        return sessions.contains(s);
    }
    public void removeRoomByUser(String roomId, WebSocketSession s){
        Set<WebSocketSession> sessions = ROOM_MAP.get(roomId);
        if(!CollectionUtils.isEmpty(sessions)){
            sessions.remove(s);
        }
    }
}