package com.keyware.MR.service;

import com.keyware.MR.entity.Child;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 子字典 服务类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-14
 */
public interface ChildService extends IService<Child> {

    String dataVal(Child child);
}
