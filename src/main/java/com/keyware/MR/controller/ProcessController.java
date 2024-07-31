package com.keyware.MR.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.keyware.MR.entity.Data;
import com.keyware.MR.entity.Faultlibrary;
import com.keyware.MR.entity.Process;
import com.keyware.MR.service.DataService;
import com.keyware.MR.service.ProcessService;
import com.keyware.MR.util.AjaxMessage;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "工序相关", tags = "工序相关")
@Controller
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Process>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Process> aPage = processService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Process> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(processService.getById(id), HttpStatus.OK);
    }
    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Process params,@RequestParam Faultlibrary faultlibrary) {
        System.out.println(faultlibrary);
        if ( processService.save(params)) {
            return new ResponseEntity<>(new AjaxMessage<Process>("1","创建成功",params,null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AjaxMessage<Process>("0","创建失败",params,null), HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        if ( processService.delete2(id)) {
            return new ResponseEntity<>(new AjaxMessage<String>("1","删除成功",id,null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AjaxMessage<String>("0","删除失败",id,null), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Process params) {
        if ( processService.updateById(params)) {
            return new ResponseEntity<>(new AjaxMessage<Process>("1","更新成功",params,null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new AjaxMessage<Process>("0","更新失败",params,null), HttpStatus.OK);
    }
}
