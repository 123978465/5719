package com.keyware.MR.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import com.keyware.MR.entity.Department;
import com.keyware.MR.service.LogService;
import com.keyware.MR.util.AjaxMessage;
import com.keyware.MR.util.IdGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.keyware.MR.service.DeviceService;
import com.keyware.MR.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * <p>
 * 设备管理表 前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@Controller
@Api(value = "设备管理", tags = "设备管理")
@RequestMapping("/device")
public class DeviceController {


    @Autowired
    private DeviceService deviceService;
    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @GetMapping(value = "/")
    @ApiOperation(value = "查询设备列表", notes = "查询设备列表")
    public ResponseEntity<Page<Device>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize, String name) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        //根据设备名称模糊查询
        QueryWrapper<Device> deviceQueryWrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(name)){
            deviceQueryWrapper.like("name",name);
        }
        Page<Device> aPage = deviceService.page(new Page<>(current, pageSize),deviceQueryWrapper);
        //保存操作日志
        logService.savelog("查询设备列表",(String) request.getSession().getAttribute("userName"));

        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Device> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(deviceService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @ApiOperation(value = "添加设备", notes = "添加设备")
    public ResponseEntity<AjaxMessage<Device>> create(@RequestBody Device params) {
        String dataVal = deviceService.dataVal(params);
        if(!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Device>>(new AjaxMessage<Device>("0",dataVal,params,null), HttpStatus.OK);
        }
        params.setId(IdGenerator.uuid32());
        params.setCreateTime(new Date());
        boolean save = deviceService.save(params);
        if (save){
            //保存操作日志
            logService.savelog("添加设备:"+params.getName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Device>>(new AjaxMessage<Device>("1","添加成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Device>>(new AjaxMessage<Device>("0","添加失败",params,null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除设备", notes = "删除设备")
    public ResponseEntity<AjaxMessage<String>> delete(@PathVariable("id") String id) {
        Device byId = deviceService.getById(id);
        boolean bool = deviceService.removeById(id);
        //保存操作日志
        logService.savelog("删除设备:"+byId.getName(),(String) request.getSession().getAttribute("userName"));
        if (bool){
            //保存操作日志
            logService.savelog("删除设备:"+byId.getName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("1","删除成功",id,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("0","删除失败",id,null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "编辑设备", notes = "编辑设备")
    public ResponseEntity<AjaxMessage<Device>> delete(@RequestBody Device params) {
        String dataVal = deviceService.dataVal(params);
        if(!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Device>>(new AjaxMessage<Device>("0","编辑失败",params,null), HttpStatus.OK);
        }
        boolean bool = deviceService.updateById(params);
        //保存操作日志
        logService.savelog("编辑设备:"+params.getName(),(String) request.getSession().getAttribute("userName"));
        if (bool){
            //保存操作日志
            logService.savelog("编辑设备:"+params.getName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Device>>(new AjaxMessage<Device>("1","编辑成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Device>>(new AjaxMessage<Device>("0","编辑失败",params,null), HttpStatus.OK);
        }
    }
}
