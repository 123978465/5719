package com.keyware.MR.service;

import com.keyware.MR.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
public interface LogService extends IService<Log> {
    /*
     * Description: 保存操作日志
     * @param operate 描述信息
     * @param userName 用户名称
     * @Return:
     * @Author: caizhihui
     * @Date: 2023/12/12 11:11
     */
    void savelog(String operate,String userName);
}
