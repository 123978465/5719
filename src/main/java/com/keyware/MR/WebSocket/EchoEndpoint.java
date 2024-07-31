//package com.keyware.MR.WebSocket;
//
//import javax.websocket.OnMessage;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//
//@ServerEndpoint("/echo")
//public class EchoEndpoint {
//    @OnMessage
//    public void onMessage(Session session, String msg) {
//        try {
//            session.getBasicRemote().sendText(msg);
//        } catch (IOException e) { ... }
//    }
//}
