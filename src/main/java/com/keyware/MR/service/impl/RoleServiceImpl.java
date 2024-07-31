package com.keyware.MR.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.keyware.MR.entity.Department;
import com.keyware.MR.entity.Role;
import com.keyware.MR.entity.Rolemenu;
import com.keyware.MR.entity.User;
import com.keyware.MR.mapper.RoleMapper;
import com.keyware.MR.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.keyware.MR.service.RolemenuService;
import com.keyware.MR.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    RolemenuService rolemenuService;
    @Autowired
    UserService userService;
    @Override
    public <E extends IPage<Role>> E page(E page, Wrapper<Role> queryWrapper) {
        E e = super.page(page, queryWrapper);
        List<Role> records = e.getRecords();
        for (Role role:records){
            getMenu(role);
            getUserList(role);
        }
        return e;
    }

    @Override
    public Role getById(Serializable id) {
        Role role = super.getById(id);
        getMenu(role);
        getUserList(role);
        return role;
    }

    /**
     * 获取角色权限菜单集合
     * @param role
     * @author YaoJz
     * @date 2024/03/29 17:24
     */
    private void getMenu(Role role){
        try{
            QueryWrapper<Rolemenu> wrapper = new QueryWrapper<>();
            wrapper.eq("role_id",role.getId());
            List<Rolemenu> rolemenuList = rolemenuService.list(wrapper);
            ArrayList<String> menuStrList = new ArrayList<>();
            rolemenuList.stream().forEach(rolemenu -> {menuStrList.add(rolemenu.getMenuId());});
            role.setMenuIds(menuStrList);
        }catch (Exception e){
            log.error("根据角色查找菜单ids时出错",e);
        }
    }
    /**
     * 获取角色列表
     * @author YaoJz
     * @date 2024/03/29 19:41
     */
    private void  getUserList(Role role){
        try{
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("role_id",role.getId());
        ArrayList<User> userList = new ArrayList<>();
        List<User> list = userService.list(wrapper);
        list.stream().forEach(user -> {userList.add(user);});
        role.setUsers(userList);
        }catch (Exception e){
            log.error("根据角色查找用户集合时出错",e);
        }
    }

    @Override
    public String dataVal(Role role) {
        return nullVal(role);
    }
    /**
     * 非空校验
     * @param role
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String nullVal(Role role) {
        StringBuilder builder = new StringBuilder();
        if (null==role.getRoleName()){
            builder.append("角色名称不能为空");
        }

        if (builder.length()==0){
            return lenVal(role);
        }
        return builder.toString();
    }
    /**
     * 长度校验
     * @param role
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String lenVal(Role role){
        StringBuilder builder = new StringBuilder();
        if (role.getRoleName().length()>50){
            builder.append("部门名称最长为50个字符");
        }
        if (role.getDescribe()!=null&&role.getDescribe().length()>225){
            builder.append("角色描述最长为225个字符");
        }
        return builder.toString();
    }
}
