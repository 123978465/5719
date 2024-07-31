package com.keyware.MR.service.impl;

import com.keyware.MR.entity.Faultphenomenon;
import com.keyware.MR.entity.Technician;
import com.keyware.MR.mapper.TechnicianMapper;
import com.keyware.MR.service.TechnicianService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 技术人员管理表 服务实现类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-14
 */
@Service
public class TechnicianServiceImpl extends ServiceImpl<TechnicianMapper, Technician> implements TechnicianService {

    @Override
    public String dataVal(Technician technician) {
        return nullVal(technician);
    }
    /**
     * 非空校验
     * @param technician
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38

     */
    private String nullVal(Technician technician) {
        StringBuilder builder = new StringBuilder();
        if (technician.getName()==null|| technician.getName().equals("")){
            builder.append("姓名不能为空");
        }
        if (technician.getGender()==null|| technician.getGender().equals("")){
            builder.append("性别不能为空");
        }
        if (technician.getPhoneNumber()==null|| technician.getPhoneNumber().equals("")){
            builder.append("手机号不能为空");
        }
        if (builder.length()==0){
            return  lenVal(technician);
        }
        return builder.toString();
    }
    /**
     * 长度校验
     * @param technician
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String lenVal(Technician technician){
        StringBuilder builder = new StringBuilder();
        if (technician.getName().length()>50){
            builder.append("姓名长度不能超过50");
        }
        if ( technician.getPhoneNumber().length()>20){
            builder.append("手机号长度不能超过20");
        }
        return builder.toString();
    }
}
