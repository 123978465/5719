//package com.keyware.MR.WebSocket;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Component;
//
//@Component
//@Qualifier("myWebSocketHandler")
//public class MyWebSocketHandler implements WebSocketHandler {
//    public static final Map<String, WebSocketSession> userSocketSessionMap;
//
//    static {
//        userSocketSessionMap = new HashMap<String, WebSocketSession>();
//    }
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
//        //建立连接之后执行
//        Map<String, Object> attributes = webSocketSession.getAttributes();
//        Object userId = attributes.get("userId");
//        if (userId != null) {
//            System.out.println("WebSocket连接成功：" + userId);
//            userSocketSessionMap.put(userId.toString(), webSocketSession);
//        } else {
//            System.out.println("WebSocket找不到userId");
//        }
//    }
//
//    //这里是我自定义的发送消息的方法
//    public void sendMessageToUser(String userName, Integer category, String msg) throws
//            IOException {
//        Iterator<Map.Entry<String, WebSocketSession>> it = userSocketSessionMap
//                .entrySet().iterator();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("type", category);
//        map.put("msg", msg);
//        final TextMessage message = new TextMessage(JsonHelper.beanToJson(map));
//        while (it.hasNext()) {
//            final Map.Entry<String, WebSocketSession> user = it.next();
//            if (user.getKey().equals(userName) && user.getValue().isOpen()) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            if (user.getValue().isOpen()) {
//                                user.getValue().sendMessage(message);
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
//            }
//        }
//    }
//
//    @Override
//    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
//        //接收到消息执行
//    }
//
//    @Override
//    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
//
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
//        //连接关闭之后执行
//    }
//
//    @Override
//    public boolean supportsPartialMessages() {
//        return false;
//    }
//}
