package com.keyware.MR.service.impl;

import com.keyware.MR.entity.Device;
import com.keyware.MR.entity.Faultphenomenon;
import com.keyware.MR.mapper.FaultphenomenonMapper;
import com.keyware.MR.service.FaultphenomenonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 故障现象表 服务实现类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-15
 */
@Service
public class FaultphenomenonServiceImpl extends ServiceImpl<FaultphenomenonMapper, Faultphenomenon> implements FaultphenomenonService {

    @Override
    public String dataVal(Faultphenomenon faultphenomenon) {
        return nullVal(faultphenomenon);
    }
    /**
     * 非空校验
     * @param faultphenomenon
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String nullVal(Faultphenomenon faultphenomenon) {
        StringBuilder builder = new StringBuilder();
        if (faultphenomenon.getFaultPhenomenon()==null||faultphenomenon.getFaultPhenomenon().equals("")){
            builder.append("故障现象不能为空");
        }
        if (faultphenomenon.getDescrib()==null|| faultphenomenon.getDescrib().equals("")){
            builder.append("故障描述不能为空");
        }
        if (builder.length()==0){
            return  lenVal(faultphenomenon);
        }
        return builder.toString();
    }
    /**
     * 长度校验
     * @param faultphenomenon
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String lenVal(Faultphenomenon faultphenomenon){
        StringBuilder builder = new StringBuilder();
        if (faultphenomenon.getFaultPhenomenon().length()>50){
            builder.append("故障现象长度不能超过50");
        }
        if ( faultphenomenon.getDescrib().length()>225){
            builder.append("故障描述不能超过225");
        }
        return builder.toString();
    }
}
