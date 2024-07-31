package com.keyware.MR.service;

import com.keyware.MR.entity.Child;
import com.keyware.MR.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-13
 */
public interface UserService extends IService<User> {
    String dataVal(User user);

}
