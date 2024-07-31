package com.keyware.MR.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import com.keyware.MR.entity.Dictionary;
import com.keyware.MR.entity.Equipment;
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
import com.keyware.MR.service.TechnicianService;
import com.keyware.MR.entity.Technician;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 技术人员管理表 前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-14
 */
@Controller
@Api(value = "技术人员管理", tags = "技术人员管理")
@RequestMapping("/technician")
public class TechnicianController {


    @Autowired
    private TechnicianService technicianService;
    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @GetMapping(value = "/")
    @ApiOperation(value = "查询技术人员列表", notes = "查询技术人员列表")
    public ResponseEntity<AjaxMessage< Page<Technician>>> list(String name, @RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        //根据姓名模糊查询
        QueryWrapper<Technician> technicianQueryWrapper = new QueryWrapper<>();
        if (StringUtil.isNotEmpty(name)){
            technicianQueryWrapper.like("name",name);
        }
        Page<Technician> aPage = technicianService.page(new Page<>(current, pageSize),technicianQueryWrapper);
        logService.savelog("查询技术人员列表",(String) request.getSession().getAttribute("userName"));
        return  new ResponseEntity< AjaxMessage< Page<Technician>>>(new AjaxMessage< Page<Technician>>("1","查询成功",aPage,null),HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AjaxMessage< Technician>> getById(@PathVariable("id") String id) {
        return  new ResponseEntity< AjaxMessage< Technician>>(new AjaxMessage< Technician>("1","查询成功",technicianService.getById(id),null),HttpStatus.OK);

    }

    @PostMapping(value = "/create")
    @ApiOperation(value = "新增技术人员", notes = "新增技术人员")
    public ResponseEntity<AjaxMessage<Technician>> create(@RequestBody Technician params) {
        String dataVal = technicianService.dataVal(params);
        if (!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Technician>>(new AjaxMessage<Technician>("0",dataVal,params,null), HttpStatus.OK);
        }
        params.setId(IdGenerator.uuid32());
        boolean save = technicianService.save(params);
        if (save){
            //保存操作日志
            logService.savelog("新增技术人员",(String) request.getSession().getAttribute("userName"));

            return new ResponseEntity<AjaxMessage<Technician>>(new AjaxMessage<Technician>("1","添加成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Technician>>(new AjaxMessage<Technician>("0","添加失败",params,null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除技术人员", notes = "删除技术人员")
    public ResponseEntity<AjaxMessage<String>> delete(@PathVariable("id") String id) {
        boolean bool = technicianService.removeById(id);
        if (bool){
            //保存操作日志
            logService.savelog("删除技术人员",(String) request.getSession().getAttribute("userName"));

            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("1","删除成功",id,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("0","删除失败",id,null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "编辑技术人员", notes = "编辑技术人员")
    public ResponseEntity<AjaxMessage<Technician>> delete(@RequestBody Technician params) {
        String dataVal = technicianService.dataVal(params);
        if (!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Technician>>(new AjaxMessage<Technician>("0",dataVal,params,null), HttpStatus.OK);
        }
        boolean bool = technicianService.updateById(params);
        if (bool){
            //保存操作日志
            logService.savelog("编辑技术人员",(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Technician>>(new AjaxMessage<Technician>("1","编辑成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Technician>>(new AjaxMessage<Technician>("0","编辑失败",params,null), HttpStatus.OK);
        }
    }
}
