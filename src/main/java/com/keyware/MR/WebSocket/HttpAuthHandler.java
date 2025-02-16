package com.keyware.MR.WebSocket;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.time.LocalDateTime;
import java.util.HashMap;

@Component
public class HttpAuthHandler extends TextWebSocketHandler {

    /**
     * socket 建立成功事件
     *
     * @param session
     * @throws Exception
     */
   public  static WebSocketSession session = null;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object sessionId = session.getAttributes().get("session_id");
        if (sessionId != null) {
            // 用户连接成功，放入在线用户缓存
            this.session= session;
            WsSessionManager.add(sessionId.toString(), session);
            System.out.println("(HttpAuthHandler)用户连接成功，放入在线用户缓存");
        } else {
            throw new RuntimeException("用户登录已经失效!");
        }
    }

    /**
     * 接收消息事件
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获得客户端传来的消息
        String payload = message.getPayload();
        Object sessionId = session.getAttributes().get("session_id");
        System.out.println("server 接收到 " + sessionId + " 发送的 " + payload);
        session.sendMessage(new TextMessage("server 发送给 " + sessionId + " 消息 " + payload + " " + LocalDateTime.now().toString()));
    }

    /**
     * socket 断开连接时
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Object sessionId = session.getAttributes().get("session_id");
        if (sessionId != null) {
            // 用户退出，移除缓存
            System.out.println("用户退出，移除缓存");
            WsSessionManager.remove(sessionId.toString());
        }
    }


}

