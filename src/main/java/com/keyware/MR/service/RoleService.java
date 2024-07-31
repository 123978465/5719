package com.keyware.MR.service;

import com.keyware.MR.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
public interface RoleService extends IService<Role> {
    String  dataVal(Role role);
}
