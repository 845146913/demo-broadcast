package com.example.demo.push;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.WebSocketSession;

public class SessionCache {
    
    /**
     * 房间链接缓存
     */
    private static final Map<String, Set<WebSocketSession>> ROOM_MAP = new ConcurrentHashMap<>();
    /**
     * 在线链接缓存
     */
    private static final CopyOnWriteArraySet<WebSocketSession> USERS = new CopyOnWriteArraySet<>();

    public static void putUser(WebSocketSession session) {
        USERS.add(session);
    }

    public static void removeUser(WebSocketSession s) {
        USERS.remove(s);
    }

    public static Set<WebSocketSession> getUsers() {
        return USERS;
    }

    public static Map<String, Set<WebSocketSession>> getRoomMap() {
        return ROOM_MAP;
    }

    public static Set<WebSocketSession> getRoomMapById(String roomId) {
        return ROOM_MAP.get(roomId);
    }

    public static void putRoom(String roomId, WebSocketSession s) {
        Set<WebSocketSession> sessions = ROOM_MAP.get(roomId);
        if(CollectionUtils.isEmpty(sessions)){
            sessions = new CopyOnWriteArraySet<>();
        }
        sessions.add(s);
        ROOM_MAP.put(roomId, sessions);
    }
    public static void removeRoom(String roomId){
        ROOM_MAP.remove(roomId);
    }
    public static boolean roomHasUser(String roomId, WebSocketSession s){
        Set<WebSocketSession> sessions = getRoomMapById(roomId);
        if(CollectionUtils.isEmpty(sessions)){
            return false;
        }
        return sessions.contains(s);
    }
    public static void removeRoomByUser(String roomId, WebSocketSession s){
        Set<WebSocketSession> sessions = ROOM_MAP.get(roomId);
        if(!CollectionUtils.isEmpty(sessions)){
            sessions.remove(s);
        }
    }
}