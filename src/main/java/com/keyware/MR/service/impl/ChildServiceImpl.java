package com.keyware.MR.service.impl;

import com.keyware.MR.entity.Child;
import com.keyware.MR.entity.Department;
import com.keyware.MR.mapper.ChildMapper;
import com.keyware.MR.service.ChildService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 子字典 服务实现类
 * </p>
 *
 * @author caizhihui
 * @since 2023-12-14
 */
@Service
public class ChildServiceImpl extends ServiceImpl<ChildMapper, Child> implements ChildService {


    @Override
    public String dataVal(Child child) {
        return nullVal(child);
    }
    /**
     * 非空校验
     * @param child
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String nullVal(Child child) {
        StringBuilder builder = new StringBuilder();
        if (child.getPid()==null||child.getPid().equals("")){
            builder.append("未发现绑定的字典项");
        }
        if (child.getName()==null||child.getName().equals("")){
            builder.append("部门名称不能为空");
        }
        if (builder.length()==0){
            return lenVal(child);
        }
        return builder.toString();
    }
    /**
     * 长度校验
     * @param child
     * @return java.lang.String
     * @author YaoJz
     * @date 2024/04/01 09:38
     */
    private String lenVal(Child child){
        StringBuilder builder = new StringBuilder();
        if (child.getName().length()>50){
            builder.append("字典项值名称最长为50个字符");
        }
        if (child.getDescrib()!=null&&!child.getDescrib().equals("")&&child.getDescrib().length()>225){
            builder.append("描述最长为225个字符");
        }
        return builder.toString();
    }
}
