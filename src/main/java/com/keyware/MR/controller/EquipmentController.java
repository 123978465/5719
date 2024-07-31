package com.keyware.MR.controller;

import cn.hutool.http.server.HttpServerRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.keyware.MR.entity.Device;
import com.keyware.MR.entity.User;
import com.keyware.MR.service.LogService;
import com.keyware.MR.util.AjaxMessage;
import com.keyware.MR.util.IdGenerator;
import com.taobao.api.internal.toplink.endpoint.Identity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.keyware.MR.service.EquipmentService;
import com.keyware.MR.entity.Equipment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 装备管理表 前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@Controller
@Api(value = "装备管理", tags = "装备管理")
@RequestMapping("/equipment")
public class EquipmentController {


    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @GetMapping(value = "/")
    @ApiOperation(value = "查询装备列表", notes = "查询装备列表")
    public ResponseEntity<AjaxMessage<List>> list() {
        List<Equipment> list = equipmentService.list();
        //保存操作日志
        logService.savelog("查询装备列表",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<AjaxMessage<List>>(new AjaxMessage<List>("1","查询成功",list,null),HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AjaxMessage<Equipment>> getById(@PathVariable("id") String id) {
        return new ResponseEntity<AjaxMessage<Equipment>>(new AjaxMessage<Equipment>("1","查询成功",equipmentService.getById(id),null),HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @ApiOperation(value = "添加装备", notes = "添加装备")
    public ResponseEntity<AjaxMessage<Equipment>> create(@RequestBody Equipment params) {
        String dataVal = equipmentService.dataVal(params);
        if (!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Equipment>>(new AjaxMessage<Equipment>("0",dataVal,params,null), HttpStatus.OK);
        }
        //赋值id
        params.setId(IdGenerator.uuid32());
        params.setCreateTime(new Date());
        boolean save = equipmentService.save(params);
        if (save){
            //保存操作日志
            logService.savelog("添加装备:"+params.getName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Equipment>>(new AjaxMessage<Equipment>("1","添加成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Equipment>>(new AjaxMessage<Equipment>("0","添加失败",params,null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除装备", notes = "删除装备")
    public ResponseEntity<AjaxMessage<String>> delete(@PathVariable("id") String id) {
        //根据装备id查询下面是否有子装备
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",id);
        List<Equipment> list = equipmentService.list(queryWrapper);
        //循环删除子装备
        for (Equipment equipment : list) {
            equipmentService.removeById(equipment.getId());
            //保存操作日志
            logService.savelog("删除装备:"+equipment.getName(),(String) request.getSession().getAttribute("userName"));
        }
        //删除选中的装备
        Equipment byId = equipmentService.getById(id);
        boolean bool = equipmentService.removeById(id);
        //保存操作日志
        if (bool){
            //保存操作日志
            logService.savelog("删除装备:"+byId.getName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("1","删除成功",id,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("0","删除失败",id,null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "编辑装备", notes = "编辑装备")
    public ResponseEntity<AjaxMessage<Equipment>> delete(@RequestBody Equipment params) {
        String dataVal = equipmentService.dataVal(params);
        if (!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Equipment>>(new AjaxMessage<Equipment>("0",dataVal,params,null), HttpStatus.OK);
        }
        boolean bool = equipmentService.updateById(params);
        if (bool){
            //保存操作日志
            logService.savelog("编辑装备:"+params.getName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Equipment>>(new AjaxMessage<Equipment>("1","编辑成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Equipment>>(new AjaxMessage<Equipment>("0","编辑失败",params,null), HttpStatus.OK);
        }
    }
}
