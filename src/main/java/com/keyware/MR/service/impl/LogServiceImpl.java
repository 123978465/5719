package com.keyware.MR.service.impl;

import com.keyware.MR.entity.Log;
import com.keyware.MR.mapper.LogMapper;
import com.keyware.MR.service.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.keyware.MR.util.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    /*
     * Description: 保存操作日志
     * @param operate 描述信息
     * @param userName 用户名称
     * @Return:
     * @Author: caizhihui
     * @Date: 2023/12/12 11:11
     */
    @Override
    public void savelog(String operate, String userName) {
        Log log = new Log();
        log.setId(IdGenerator.uuid32());
        log.setOperate(operate);
        log.setUserName(userName);
        log.setCreateTime(new Date());
        save(log);
    }
}
