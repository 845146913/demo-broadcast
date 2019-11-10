package com.example.demo.cache;

import java.util.Map;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

public interface PushCache {
    
    /**
     * 添加到user缓存
     */
    void putUser(WebSocketSession session);

    /**
     * 删除User缓存
     * @param s
     */
    void removeUser(WebSocketSession s);
    /**
     * 在线链接
     * @return
     */
    Set<WebSocketSession> getUsers();

    /**
     * 所有房间-链接数
     * @return
     */
    Map<String, Set<WebSocketSession>> getRoomMap();

    /**
     * 房间对应链接
     * @param roomId
     * @return
     */
    Set<WebSocketSession> getRoomMapById(String roomId);

    /**
     * 添加房间
     * @param roomId
     * @param s
     */
    void putRoom(String roomId, WebSocketSession s);
    /**
     * 删除房间
     * @param roomId
     */
    void removeRoom(String roomId);

    /**
     * 房间是否存在链接
     */
    boolean roomHasUser(String roomId, WebSocketSession s);
    /**
     * 删除房间-链接
     * @param roomId
     * @param s
     */
    void removeRoomByUser(String roomId, WebSocketSession s);
}