package com.keyware.MR.service;

import com.keyware.MR.entity.Equipment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 装备管理表 服务类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
public interface EquipmentService extends IService<Equipment> {
    String dataVal(Equipment equipment);
}
