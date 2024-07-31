package com.keyware.MR.controller;

import com.keyware.MR.util.AjaxMessage;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.keyware.MR.service.ChildService;
import com.keyware.MR.entity.Child;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 子字典 前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-14
 */
@Controller
@RequestMapping("/child")
public class ChildController {


    @Autowired
    private ChildService childService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Child>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Child> aPage = childService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Child> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(childService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<AjaxMessage<Child>> create(@RequestBody Child params) {
        String dataVal = childService.dataVal(params);
        if (!dataVal.equals("")) {
            return new ResponseEntity<AjaxMessage<Child>>(new AjaxMessage<Child>("0", dataVal, params, null), HttpStatus.OK);
        }
        if (childService.save(params)){
            return new ResponseEntity<AjaxMessage<Child>>(new AjaxMessage<Child>("1", "创建成功", params, null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Child>>(new AjaxMessage<Child>("0", "创建失败", params, null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<AjaxMessage<String>> delete(@PathVariable("id") String id) {
       if( childService.removeById(id)){
        return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("1", "创建成功", id, null), HttpStatus.OK);
    }else {
        return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("0", "创建失败", id, null), HttpStatus.OK);
    }
    }
    @PostMapping(value = "/update")
    public ResponseEntity<AjaxMessage<Child>> delete(@RequestBody Child params) {
        String dataVal = childService.dataVal(params);
        if (!dataVal.equals(""))  {
            return new ResponseEntity<AjaxMessage<Child>>(new AjaxMessage<Child>("0", dataVal, params, null), HttpStatus.OK);
        }
        if( childService.updateById(params)){
            return new ResponseEntity<AjaxMessage<Child>>(new AjaxMessage<Child>("1", "创建成功", params, null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Child>>(new AjaxMessage<Child>("0", "创建失败", params, null), HttpStatus.OK);
        }
    }
}
