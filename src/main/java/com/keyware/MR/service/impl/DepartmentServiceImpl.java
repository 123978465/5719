package com.keyware.MR.service.impl;

import com.keyware.MR.entity.Child;
import com.keyware.MR.entity.Department;
import com.keyware.MR.mapper.DepartmentMapper;
import com.keyware.MR.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组织管理 服务实现类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Override
    public String dataVal(Department department) {
        return nullVal(department);
    }
    /**
     * 非空校验
     * @param department
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String nullVal(Department department) {
        StringBuilder builder = new StringBuilder();
        if (null==department.getName()){
            builder.append("部门名称不能为空");
        }
        if (null==department.getPhoneNumber()){
            builder.append("负责人电话不能为空");
        }
        if (null==department.getHead()){
            builder.append("部门负责人不能为空");
        }
        if (null==department.getPeopleNumber()){
            builder.append("部门人数不能为空");
        }
        if (builder.length()==0){
            return lenVal(department);
        }
        return builder.toString();
    }
    /**
     * 长度校验
     * @param department
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String lenVal(Department department){
        StringBuilder builder = new StringBuilder();
        if (department.getName().length()>50){
            builder.append("部门名称最长为50个字符");
        }
        if (department.getPhoneNumber().length()>20){
            builder.append("负责人电话最长为20个字符");
        }
        if (department.getHead().length()>20){
            builder.append("部门负责人最长为20个字符");
        }
        if (department.getPeopleNumber()>9999){
            builder.append("部门人数超出上限(9999)");
        }
        return builder.toString();
    }
}
