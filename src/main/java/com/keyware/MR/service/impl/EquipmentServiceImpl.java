package com.keyware.MR.service.impl;

import com.keyware.MR.entity.Device;
import com.keyware.MR.entity.Equipment;
import com.keyware.MR.mapper.EquipmentMapper;
import com.keyware.MR.service.EquipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 装备管理表 服务实现类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-12
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {
   @Override
    public String dataVal(Equipment equipment) {
       return nullVal(equipment);
   }
    /**
     * 非空校验
     * @param equipment
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String nullVal(Equipment equipment) {
        StringBuilder builder = new StringBuilder();
        if (equipment.getName()==null||equipment.getName().equals("")){
            builder.append("设备名称不能为空");
        }
        if ( equipment.getModel()==null||equipment.getModel().equals("")){
            builder.append("型号不能为空");
        }
        if ( equipment.getGeneration()==null||equipment.getGeneration().equals("")){
            builder.append("代别不能为空");
        }
        if ( equipment.getManufacturer()==null||equipment.getManufacturer().equals("")){
            builder.append("生产厂家不能为空");
        }
        if ( equipment.getNumber()==null||equipment.getNumber().equals("")){
            builder.append("出厂编号不能为空");
        }
        if ( equipment.getProductionDate()==null||equipment.getProductionDate().equals("")){
            builder.append("出厂日期不能为空");
        }
        if ( equipment.getDeployment()==null||equipment.getDeployment().equals("")){
            builder.append("部署单位不能为空");
        }
        if ( equipment.getActivationDate()==null||equipment.getActivationDate().equals("")){
            builder.append("启用日期不能为空");
        }
        if ( equipment.getAccumulatedWorkingTime()==null||equipment.getAccumulatedWorkingTime().equals("")){
            builder.append("累计工作时间不能为空");
        }
        return builder.toString();
    }
    /**
     * 长度校验
     * @param equipment
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String lenVal(Equipment equipment){
        StringBuilder builder = new StringBuilder();
        if (equipment.getName().length()>50){
            builder.append("设备名称最长不超过50");
        }
        if ( equipment.getModel().length()>50){
            builder.append("型号最长不超过50");
        }
        if ( equipment.getGeneration().length()>50){
            builder.append("代别最长不超过50");
        }
        if ( equipment.getManufacturer().length()>50){
            builder.append("生产厂家最长不超过50");
        }
//        if ( equipment.getNumber()>9999999){
//            builder.append("出厂编号不能为空");
//        }
//        if ( equipment.getProductionDate().length()>50){
//            builder.append("出厂日期不能为空");
//        }
        if ( equipment.getDeployment().length()>50){
            builder.append("部署单位最长不超过50");
        }
//        if ( equipment.getActivationDate().length()>50){
//            builder.append("启用日期不能为空");
//        }
//        if ( equipment.getAccumulatedWorkingTime().length()>50){
//            builder.append("累计工作时间不能为空");
//        }
        return builder.toString();
    }
}
