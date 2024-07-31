package com.keyware.MR.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.keyware.MR.entity.Department;
import com.keyware.MR.entity.Role;
import com.keyware.MR.entity.User;
import com.keyware.MR.mapper.UserMapper;
import com.keyware.MR.service.DepartmentService;
import com.keyware.MR.service.RoleService;
import com.keyware.MR.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    RoleService roleService;
    @Autowired
    DepartmentService departmentService;
    @Override
    public <E extends IPage<User>> E page(E page, Wrapper<User> queryWrapper) {
        E e = super.page(page, queryWrapper);
        List<User> records = e.getRecords();
        for (User user:records){
            idTranName(user);
        }
        return e;
    }

    @Override
    public User getById(Serializable id) {
        User user = super.getById(id);
        idTranName(user);
        return user;
    }
    /**
     * 部门 角色 通过id获取文字描述
     * @param user
     * @author YaoJz
     * @date 2024/03/29 17:25
     */
    private void idTranName(User user){
        try{
            Department department = departmentService.getById(user.getDepartmentId());
            user.setDepartmentName(department.getName());
        }catch (Exception e){
            log.error("根据用户查找部门名称时出错",e);
        }
        try{
            Role role = roleService.getById(user.getRoleId());
            user.setRole(role.getRoleName());

        }catch (Exception e){
            log.error("根据用户查找角色名时出错",e);
        }
    }
    @Override
    public String dataVal(User user) {
        return nullVal(user);
    }
    /**
     * 非空校验
     * @param user
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String nullVal(User user) {
        StringBuilder builder = new StringBuilder();
        if (user.getUserName()==null||user.getUserName().equals("")){
            builder.append("用户名不能为空");
        }
        if ( user.getRealName()==null||user.getRealName().equals("")){
            builder.append("真实姓名不能为空");
        }
        if ( user.getGender()==null||user.getGender().equals("")){
            builder.append("性别不能为空");
        }
        if (user.getPassword()==null||user.getPassword().equals("")){
            builder.append("密码不能为空");
        }
        if (user.getPhoneNumber()==null||user.getPhoneNumber().equals("")){
            builder.append("手机号不能为空");
        }
        if (user.getDepartmentId()==null||user.getDepartmentId().equals("")){
            builder.append("部门不能为空");
        }
        if (user.getRoleId()==null||user.getRoleId().equals("")){
            builder.append("角色不能为空");
        }
        if (builder.length()==0){
            return  lenVal(user);
        }
        return builder.toString();
    }
    /**
     * 长度校验
     * @param user
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String lenVal(User user){
        StringBuilder builder = new StringBuilder();
        if (user.getUserName().length()>50){
            builder.append("用户名最长为50个字符");
        }
        if ( user.getPhoneNumber().length()>20){
            builder.append("手机号最长为20个字符");
        }
        if ( user.getGender().length()>2){
            builder.append("性别标识异常");
        }
        if (user.getPassword().length()>50){
            builder.append("手机号最长为50个字符");
        }
        return builder.toString();
    }
}
