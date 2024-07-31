package com.keyware.MR.service.impl;

import com.keyware.MR.entity.Device;
import com.keyware.MR.entity.Dictionary;
import com.keyware.MR.mapper.DictionaryMapper;
import com.keyware.MR.service.DictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-14
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Override
    public String dataVal(Dictionary dictionary){
        return nullVal(dictionary);
    }
    /**
     * 非空校验
     * @param dictionary
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String nullVal(Dictionary dictionary) {
        StringBuilder builder = new StringBuilder();
        if (dictionary.getName()==null||dictionary.getName().equals("")){
            builder.append("设备名称不能为空");
        }
        if (builder.length()==0){
            return  lenVal(dictionary);
        }
        return builder.toString();
    }
    /**
     * 长度校验
     * @param dictionary
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String lenVal(Dictionary dictionary){
        StringBuilder builder = new StringBuilder();
        if (dictionary.getName().length()>50){
            builder.append("字典项名称长度不能超过50");
        }
        if (dictionary.getDescrib()!=null&&!dictionary.getDescrib().equals("")&&dictionary.getDescrib().length()>225){
            builder.append("描述长度不能超过225");
        }
        if (dictionary.getType()!=null&&!dictionary.getType().equals("")&&dictionary.getType().length()>50){
            builder.append("字典项类型不能超过50");
        }
        return builder.toString();
    }
}
