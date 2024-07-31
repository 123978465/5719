package com.keyware.MR.service;

import com.keyware.MR.entity.Device;
import com.keyware.MR.entity.Faultphenomenon;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 故障现象表 服务类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-15
 */
public interface FaultphenomenonService extends IService<Faultphenomenon> {
    String dataVal(Faultphenomenon faultphenomenon);
}
