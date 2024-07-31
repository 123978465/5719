package com.keyware.MR.service.impl;

import com.keyware.MR.entity.Device;
import com.keyware.MR.entity.User;
import com.keyware.MR.mapper.DeviceMapper;
import com.keyware.MR.service.DeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备管理表 服务实现类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    @Override
    public String dataVal(Device device) {
        return nullVal(device);
    }
    /**
     * 非空校验
     * @param device
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String nullVal(Device device) {
        StringBuilder builder = new StringBuilder();
        if (device.getName()==null||device.getName().equals("")){
            builder.append("设备名称不能为空");
        }
        if ( device.getNumber()==null||device.getNumber().equals("")){
            builder.append("设备编号不能为空");
        }
        if ( device.getPurchaseDate()==null){
            builder.append("采购日期不能为空");
        }
        if (device.getMark()==null||device.getMark().equals("")){
            builder.append("设备标识不能为空");
        }
        if (builder.length()==0){
            return  lenVal(device);
        }
        return builder.toString();
    }
    /**
     * 长度校验
     * @param device
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String lenVal(Device device){
        StringBuilder builder = new StringBuilder();
        if (device.getName().length()>50){
            builder.append("设备名称长度不能超过50");
        }
        if ( device.getNumber().length()>50){
            builder.append("设备编号长度不能超过50");
        }
        if (device.getMark().length()>50){
            builder.append("设备标识长度不能超过50");
        }
        return builder.toString();
    }
}
