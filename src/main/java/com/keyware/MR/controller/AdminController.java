package com.keyware.MR.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.keyware.MR.config.AccessSystem;
import com.keyware.MR.config.AccessSystemConfig;
import com.keyware.MR.entity.User;
import com.keyware.MR.util.AjaxMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YaoJz
 * @description
 * @date 2024/4/2 11:57
 */
@RestController
@Api(value = "动态配置更新", tags = "动态配置更新")
@RequestMapping
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AccessSystemConfig accessSystemConfig;

    @ApiOperation(value = "查看配置", notes = "查看配置")
    @GetMapping("/getConfig")
    public ResponseEntity< Map<String, String>> getConfig() {
        Map<String, Object> result = new HashMap<>();

        //这里模拟根据配置 完成不同的逻辑
        Map<String, String> map = accessSystemConfig.getSystemList();
        map.put("code","1");
        map.put("msg","查看成功");
        return   new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }
    @ApiOperation(value = "更改配置", notes = "更改配置")
    @GetMapping("/setConfig")
    public  ResponseEntity< AjaxMessage<String>> setConfig(@RequestParam("key") String key,@RequestParam("value") String value) {
        accessSystemConfig.setValueByKey(key,value);
        return   new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("1","更改成功",null,null), HttpStatus.OK);
    }

}
