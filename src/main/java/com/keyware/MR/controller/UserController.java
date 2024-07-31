package com.keyware.MR.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import com.keyware.MR.config.minio.MinIoService;
import com.keyware.MR.entity.Department;
import com.keyware.MR.entity.Menu;
import com.keyware.MR.entity.Rolemenu;
import com.keyware.MR.service.DepartmentService;
import com.keyware.MR.service.LogService;
import com.keyware.MR.service.MenuService;
import com.keyware.MR.service.RolemenuService;
import com.keyware.MR.util.AjaxMessage;
import com.keyware.MR.util.IdGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.keyware.MR.service.UserService;
import com.keyware.MR.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-13
 */
@Controller
@Api(value = "用户管理", tags = "用户管理")
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RolemenuService rolemenuService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private LogService logService;
    @Resource
    MinIoService minIoService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private HttpServletResponse response;

    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
    @GetMapping(value = "/")
    public ResponseEntity<AjaxMessage<Page<User>>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize, User user) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        //条件查询
        //用户名
        if (StringUtil.isNotEmpty(user.getUserName())) {
            userQueryWrapper.like("user_name", user.getUserName());
        }
        //用户角色
        if (StringUtil.isNotEmpty(user.getRoleId())) {
            userQueryWrapper.eq("role_id", user.getRoleId());
        }
        //用户状态
        if (StringUtil.isNotEmpty(user.getState())) {
            userQueryWrapper.eq("state", user.getState());
        }
        //根据组织id查询下面的子组织
        List<Department> list = departmentService.list();
        //递归查询改组织下所有的子组织
        List<String> childrenIds = new ArrayList<>();
        List<String> strings = moduleRecursion(childrenIds, list, user.getDepartmentId());
        strings.add(user.getDepartmentId());
        userQueryWrapper.in("department_id",strings);
        Page<User> aPage = userService.page(new Page<>(current, pageSize), userQueryWrapper);
        return new ResponseEntity<AjaxMessage<Page<User>>>(new AjaxMessage<Page<User>>("1","查询成功",aPage,null), HttpStatus.OK);
    }

    private List<String> moduleRecursion(List<String> children, List<Department> modules, String pid) {
        for (Department department : modules) {
            //遍历出父id等于pid，add进子节点集合
            if (department.getPid().equals(pid)) {
                // 递归遍历下一级
                moduleRecursion(children, modules, department.getId());
                children.add(department.getId());
            }
        }
        return children;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<AjaxMessage<User>> getById(@PathVariable("id") String id) {
        return new ResponseEntity<AjaxMessage<User>>(new AjaxMessage<User>("1","查询成功",userService.getById(id),null), HttpStatus.OK);
    }


    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping(value = "/create")
    public ResponseEntity<AjaxMessage<User>> create(@RequestBody User user) {
        String dataVal = userService.dataVal(user);
        if(!dataVal.equals("")){
            return new ResponseEntity<AjaxMessage<User>>(new AjaxMessage<User>("0",dataVal,user,null), HttpStatus.OK);
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", user.getUserName());
        List<User> list = userService.list(userQueryWrapper);
        //校验用户名是否重复
        if (list.size() != 0) {
            return new ResponseEntity<AjaxMessage<User>>(new AjaxMessage<User>("0","用户名已存在",user,null), HttpStatus.OK);
        }
        user.setId(IdGenerator.uuid32());
        user.setCreateTime(new Date());
        boolean save = userService.save(user);
        if (save){
            logService.savelog("新增用户"+user.getUserName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<User>>(new AjaxMessage<User>("1","添加成功",user,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<User>>(new AjaxMessage<User>("0","添加失败",user,null), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<AjaxMessage<String>> delete(@PathVariable("id") String id) {
        User byId = userService.getById(id);
        boolean bool = userService.removeById(id);
        if (bool){
            logService.savelog("删除用户"+byId.getUserName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("1","删除成功",id,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<String>>(new AjaxMessage<String>("0","删除失败",id,null), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "编辑用户", notes = "编辑用户")
    @PostMapping(value = "/update")
    public ResponseEntity<AjaxMessage<User>> delete(@RequestBody User params) {
//        String dataVal = userService.dataVal(params);
//        if(!dataVal.equals("")){
//            return new ResponseEntity<AjaxMessage<User>>(new AjaxMessage<User>("0",dataVal,params,null), HttpStatus.OK);
//        }
        boolean bool = userService.updateById(params);
        if (bool){
            logService.savelog("编辑用户"+params.getUserName(),(String) request.getSession().getAttribute("userName"));
            return new ResponseEntity<AjaxMessage<User>>(new AjaxMessage<User>("1","编辑成功",params,null), HttpStatus.OK);
        }else {
            return new ResponseEntity<AjaxMessage<User>>(new AjaxMessage<User>("0","编辑失败",params,null), HttpStatus.OK);
        }
    }

    /*
     * Description: 登录
     * @param params
     * @Return: {@link org.springframework.http.ResponseEntity<java.lang.Object>}
     * @Author: caizhihui
     * @Date: 2023/12/13 10:37
     */
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody User user, HttpServletResponse response) {
        //根据用户名查询是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", user.getUserName());
        List<User> list = userService.list(userQueryWrapper);
        if (list.size() == 0) {
            return new ResponseEntity<>(new AjaxMessage<List>("0","用户不存在",null,null),HttpStatus.OK);
        } else {
            //校验密码是否正确
            if (list.get(0).getPassword().equals(user.getPassword())) {
                request.getSession().setAttribute("userId",list.get(0).getId());
                request.getSession().setAttribute("userName",list.get(0).getUserName());
                //登录成功 根据用户查询用户角色下的菜单
                QueryWrapper<Rolemenu> rolemenuQueryWrapper = new QueryWrapper<>();
                String roleId = list.get(0).getRoleId();
                rolemenuQueryWrapper.select("menu_id");
                rolemenuQueryWrapper.eq("role_id",roleId);
                List<Rolemenu> list1 = rolemenuService.list(rolemenuQueryWrapper);
                //根据菜单id查询所有菜单的详情
                ArrayList<String> strings = new ArrayList<>();
                for (Rolemenu rolemenu : list1) {
                    strings.add(rolemenu.getMenuId());
                }
                QueryWrapper<Menu> menuQueryWrapper = new QueryWrapper<>();
                menuQueryWrapper.in("id",strings);
                List<Menu> list2 = menuService.list(menuQueryWrapper);
                logService.savelog("用户"+user.getUserName()+"登录成功",(String) request.getSession().getAttribute("userName"));
                Cookie cookie = new Cookie("isLogin", "true");
                cookie.setMaxAge(300);
                response.addHeader("userId",list.get(0).getId());
                response.addCookie(cookie);
                return new ResponseEntity<>(new AjaxMessage<List>("1",list.get(0).getId(),list2,null),HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new AjaxMessage<List>("0","密码错误",null,null),HttpStatus.OK);
            }
        }
    }

    /*
     * Description: 退出
     * @param user
     * @Return: {@link org.springframework.http.ResponseEntity<java.lang.Object>}
     * @Author: caizhihui
     * @Date: 2023/12/13 10:48
     */
    @PostMapping(value = "/logout")
    public ResponseEntity<Object> logout() {
        logService.savelog("用户"+request.getSession().getAttribute("userName")+"退出登录", (String) request.getSession().getAttribute("userName"));
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("isLogin".equals(cookie.getName())) {
                cookie.setMaxAge(0);
            }
        }
        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("userName");
        return new ResponseEntity<>("退出成功", HttpStatus.OK);
    }
    @PostMapping(value = "/upload")
    @ApiOperation(value = "上传文件", notes = "上传文件")
    public ResponseEntity recovery(@RequestPart("multipartfile") MultipartFile multipartfile) {
        //将上传的备份文件保存到本地
        String s = minIoService.uploadToFile(multipartfile);
        logService.savelog("上传文件", (String) request.getSession().getAttribute("userName"));
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
