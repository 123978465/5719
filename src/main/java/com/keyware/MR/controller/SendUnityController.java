package com.keyware.MR.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.keyware.MR.WebSocket.HttpAuthHandler;
import com.keyware.MR.WebSocket.WsSessionManager;
import com.keyware.MR.entity.Data;
import com.keyware.MR.entity.FailureToUnity;
import com.keyware.MR.service.LogService;
import com.keyware.MR.util.AjaxMessage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Controller
@RequestMapping("/socket")
@Api(value = "测试socket" ,tags = "测试socket")
public class SendUnityController {

    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;
    @PostMapping(value = "/send")
     public ResponseEntity<AjaxMessage> send(@RequestBody FailureToUnity failureToUnity){
        logService.savelog("向m2端发送数据",(String) request.getSession().getAttribute("userName"));
        System.out.println("走了");

//        String failureToUnityJson = JSON.toJSONString(failureToUnity);
//        System.out.println(failureToUnityJson);
        AjaxMessage<FailureToUnity> message = new AjaxMessage<>("1", " ", failureToUnity, null);
        String messageJson = JSON.toJSONString(message);
//        FailureToUnity failureToUnity
          try {
              HttpAuthHandler.session.sendMessage(new TextMessage(messageJson));
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
          return new ResponseEntity<>(new AjaxMessage("1","qwe","qwe",null), HttpStatus.OK);
      }


}
