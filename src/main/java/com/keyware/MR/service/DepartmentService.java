package com.keyware.MR.service;

import com.keyware.MR.entity.Child;
import com.keyware.MR.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 组织管理 服务类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
public interface DepartmentService extends IService<Department> {
    String dataVal(Department department);
}
