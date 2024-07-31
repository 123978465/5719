package com.keyware.MR.service;

import com.keyware.MR.entity.Device;
import com.baomidou.mybatisplus.extension.service.IService;
import com.keyware.MR.entity.User;

/**
 * <p>
 * 设备管理表 服务类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
public interface DeviceService extends IService<Device> {
    String dataVal(Device device);
}
