package com.keyware.MR.service;

import com.keyware.MR.entity.Faultphenomenon;
import com.keyware.MR.entity.Technician;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 技术人员管理表 服务类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-14
 */
public interface TechnicianService extends IService<Technician> {
    String dataVal(Technician technician);
}
