package com.keyware.MR.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.keyware.MR.entity.Faultlibrary;
import com.keyware.MR.entity.Process;
import com.keyware.MR.mapper.FaultlibraryMapper;
import com.keyware.MR.mapper.ProcessMapper;
import com.keyware.MR.service.FaultlibraryService;
import com.keyware.MR.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process> implements ProcessService {
    @Autowired
    FaultlibraryMapper faultlibraryMapper;
    @Autowired
    FaultlibraryService faultlibraryService;

    @Override
    public boolean save2(Process process) {
        return save(process);
    }

    @Override
    public boolean update2(Process process) {
        process.setExecutionStepNum(faultlibraryMapper.faultNumberForProcessId(process.getID()));
        return updateById(process);
    }

    @Override
    public boolean delete2(String id) {
        QueryWrapper<Faultlibrary> faultlibraryWrapper = new QueryWrapper<>();
        faultlibraryWrapper.lambda().eq(Faultlibrary::getProcessId,id);
        faultlibraryService.remove(faultlibraryWrapper);
        return removeById(id);
    }
}
