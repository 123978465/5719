package com.keyware.MR.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.keyware.MR.entity.Process;
import org.springframework.stereotype.Service;


@Service
public interface ProcessService extends IService<Process> {


    public boolean save2(Process process);
    public boolean update2(Process process);
    public boolean delete2(String id);
}
