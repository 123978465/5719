package com.keyware.MR.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.keyware.MR.entity.Department;
import com.keyware.MR.entity.Rolemenu;
import com.keyware.MR.service.LogService;
import com.keyware.MR.service.RolemenuService;
import com.keyware.MR.util.AjaxMessage;
import com.keyware.MR.util.IdGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.keyware.MR.service.RoleService;
import com.keyware.MR.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Wrapper;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@Controller
@Api(value = "角色管理", tags = "角色管理")
@RequestMapping("/role")
public class RoleController {


    @Autowired
    private RoleService roleService;
    @Autowired
    private RolemenuService rolemenuService;
    @Autowired
    private LogService logService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @GetMapping(value = "/")
    public ResponseEntity<AjaxMessage<Page<Role>>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Role> aPage = roleService.page(new Page<>(current, pageSize));
        logService.savelog("查询角色列表",(String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<AjaxMessage<Page<Role>>>(new AjaxMessage<Page<Role>>("1","查询成功",aPage,null), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AjaxMessage<Role>> getById(@PathVariable("id") String id) {
        return new ResponseEntity<AjaxMessage<Role>>(new AjaxMessage<Role>("1","查询成功",roleService.getById(id),null), HttpStatus.OK);
    }

    @ApiOperation(value = "创建角色", notes = "创建角色")
    @PostMapping(value = "/create")
    public ResponseEntity<AjaxMessage<Role>> create(@RequestBody Role params, @RequestParam("ids") List<String> ids) {
        if (ids.size()==0){
            return new ResponseEntity<AjaxMessage<Role>>(new AjaxMessage<Role>("0","请为角色授权",params,null), HttpStatus.OK);
        }
        String dataVal = roleService.dataVal(params);
        if(!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Role>>(new AjaxMessage<Role>("0",dataVal,params,null), HttpStatus.OK);
        }
        String uuid32 = IdGenerator.uuid32();
        params.setId(uuid32);
        params.setCreateTime(new Date());
        boolean save = roleService.save(params);
        //循环插入菜单角色关系表
        for (String id : ids) {
            Rolemenu rolemenu = new Rolemenu();
            rolemenu.setMenuId(id);
            rolemenu.setRoleId(uuid32);
            rolemenuService.save(rolemenu);
        }
        if (save){
            logService.savelog("创建角色"+params.getRoleName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Role>>(new AjaxMessage<Role>("1","添加成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Role>>(new AjaxMessage<Role>("0","添加失败",params,null), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<AjaxMessage<String>> delete(@PathVariable("id") String id) {
        Role byId = roleService.getById(id);
        boolean bool = roleService.removeById(id);
        //删除菜单角色关系表数据
        UpdateWrapper<Rolemenu> rolemenuUpdateWrapper = new UpdateWrapper<>();
        rolemenuUpdateWrapper.eq("role_id",id);
        rolemenuService.remove(rolemenuUpdateWrapper);
        if (bool){
            logService.savelog("删除角色"+byId.getRoleName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("1","删除成功",id,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("0","删除失败",id,null), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "编辑角色", notes = "编辑角色")
    @PostMapping(value = "/update")
    public ResponseEntity<AjaxMessage<Role>> delete(@RequestBody Role params, @RequestParam("ids")  List<String> ids) {
        String dataVal = roleService.dataVal(params);
        if(!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<Role>>(new AjaxMessage<Role>("0",dataVal,params,null), HttpStatus.OK);
        }
        boolean bool = roleService.updateById(params);
        //删除菜单角色关系表数据
        UpdateWrapper<Rolemenu> rolemenuUpdateWrapper = new UpdateWrapper<>();
        rolemenuUpdateWrapper.eq("role_id",params.getId());
        rolemenuService.remove(rolemenuUpdateWrapper);
        //循环插入菜单角色关系表
        for (String id : ids) {
            Rolemenu rolemenu = new Rolemenu();
            rolemenu.setMenuId(id);
            rolemenu.setRoleId(params.getId());
            rolemenuService.save(rolemenu);
        }
        if (bool){
            logService.savelog("编辑角色"+params.getRoleName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<Role>>(new AjaxMessage<Role>("1","编辑成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<Role>>(new AjaxMessage<Role>("0","编辑失败",params,null), HttpStatus.OK);
        }
    }
}
