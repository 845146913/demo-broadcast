package com.example.demo.push;

import java.util.Map;
import java.util.Set;

import com.example.demo.utils.JsonMapper;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class MountPush implements Push {

    private final MessageSender sender;

    @Override
    public void dowork(WebSocketSession curSession, TextMessage message) {
        String payload = message.getPayload();
        String name = curSession.getPrincipal().getName();
        Map<String, Object> map = JsonMapper.jsonToObj(payload, Map.class);
        if (!CollectionUtils.isEmpty(map)) {
            String msgType = map.get("msgType") + "";
            String roomId = map.get("roomId") + "";
            map.put("fromId", name);
            if (null != msgType && msgType.equals("1")) {
                SessionCache.putRoom(roomId, curSession);
                map.put("content", name+"加入房间");
                String msg = JsonMapper.objToJson(map);
                Set<WebSocketSession> users = SessionCache.getRoomMapById(roomId);
                sender.broadcast(users, new TextMessage(msg));
            }
            if (null != msgType && msgType.equals("2")) {
                if(SessionCache.roomHasUser(roomId, curSession)){
                    map.put("content", name+"离开房间");
                    String msg = JsonMapper.objToJson(map);
                    Set<WebSocketSession> users = SessionCache.getRoomMapById(roomId);
                    sender.broadcast(users, new TextMessage(msg));
                    SessionCache.removeRoomByUser(roomId, curSession);
                }
            }
        } else {
            sender.unicast(curSession, message);
        }
    }
    
}