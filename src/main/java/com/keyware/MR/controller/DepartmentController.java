package com.keyware.MR.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.keyware.MR.entity.Data;
import com.keyware.MR.entity.Equipment;
import com.keyware.MR.entity.Log;
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
import com.keyware.MR.service.DepartmentService;
import com.keyware.MR.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 组织管理 前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@Controller
@Api(value = "组织管理", tags = "组织管理")
@RequestMapping("/department")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @GetMapping(value = "/")
    @ApiOperation(value = "查询组织结构列表", notes = "查询组织结构列表")
    public ResponseEntity<AjaxMessage<List>> list() {
        List<Department> list = departmentService.list();
        //保存操作日志
        logService.savelog("查询组织结构列表",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<AjaxMessage<List>>(new AjaxMessage<List>("1","查询成功",list,null), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AjaxMessage<Department>> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(new AjaxMessage<Department>("1","查询成功",departmentService.getById(id),null), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    @ApiOperation(value = "添加部门", notes = "添加部门")
    public ResponseEntity<AjaxMessage<Department>> create(@RequestBody Department params) {
        String dataVal = departmentService.dataVal(params);
        if(!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Department>>(new AjaxMessage<Department>("0",dataVal,params,null), HttpStatus.OK);
        }
        //赋值id
        params.setId(IdGenerator.uuid32());
        params.setCreateTime(new Date());
        boolean bool = departmentService.save(params);
        if (bool){
            logService.savelog("添加部门:"+params.getName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Department>>(new AjaxMessage<Department>("1","添加成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Department>>(new AjaxMessage<Department>("0","添加失败",params,null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/delete/{id}")
    @ApiOperation(value = "删除部门", notes = "删除部门")
    public ResponseEntity<AjaxMessage<String>> delete(@PathVariable("id") String id) {
        //根据部门id查询下面是否有子部门
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",id);
        List<Department> list = departmentService.list(queryWrapper);
        //循环删除子部门
        for (Department department : list) {
            departmentService.removeById(department.getId());
            logService.savelog("删除部门:"+department.getName(),(String) request.getSession().getAttribute("userName"));
        }
        //删除部门
        Department byId = departmentService.getById(id);
        boolean bool = departmentService.removeById(id);
        if (bool){
            logService.savelog("删除部门:"+byId.getName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("1","删除成功",id,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("0","删除失败",id,null), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "编辑部门", notes = "编辑部门")
    public ResponseEntity<AjaxMessage<Department>> delete(@RequestBody Department params) {
        String dataVal = departmentService.dataVal(params);
        if(!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Department>>(new AjaxMessage<Department>("0",dataVal,params,null), HttpStatus.OK);
        }
        boolean bool = departmentService.updateById(params);
        if (bool){
            logService.savelog("编辑部门:"+params.getName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Department>>(new AjaxMessage<Department>("1","编辑成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Department>>(new AjaxMessage<Department>("0","编辑失败",params,null), HttpStatus.OK);
        }
    }
}
