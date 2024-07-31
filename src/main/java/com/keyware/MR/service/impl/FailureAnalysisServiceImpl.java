package com.keyware.MR.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.keyware.MR.Emulator.Fault;
import com.keyware.MR.entity.FailureAnalysisInfo;
import com.keyware.MR.entity.Faultlibrary;
import com.keyware.MR.entity.Process;
import com.keyware.MR.service.FailureAnalysisService;
import com.keyware.MR.service.FaultlibraryService;
import com.keyware.MR.service.ProcessService;
import com.keyware.MR.util.AjaxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FailureAnalysisServiceImpl implements FailureAnalysisService {

    @Autowired
    FaultlibraryService faultlibraryService;
    @Autowired
    ProcessService processService;

    @Override
    public AjaxMessage<FailureAnalysisInfo> getAnalysisInfoByPId(Integer processId) {
        QueryWrapper<Faultlibrary> faultlibraryWrapper = new QueryWrapper<>();
        faultlibraryWrapper.eq("PROCESSID", processId);
        List<Faultlibrary> faultlibraryList = faultlibraryService.list(faultlibraryWrapper);
        Process process = processService.getById(processId);
        AjaxMessage<FailureAnalysisInfo> message = new AjaxMessage<>();
        message.setCode("1");
        message.setMessage("");
        message.setData(setProcess(faultlibraryList,process));
        return message;
    }

    private FailureAnalysisInfo setProcess( List<Faultlibrary>  faultlibraryList,Process process){
        String processName = process.getProcess();
        for (Faultlibrary faultlibrary : faultlibraryList){
            faultlibrary.setProcess(processName);
        }
        return new FailureAnalysisInfo(faultlibraryList,process);
    }

   public String dataGeneration(String max,String min){
      return Fault.calculationData(max, min);
    }

    @Override
    public AjaxMessage<List<FailureAnalysisInfo>> getInfoList() {
        List<FailureAnalysisInfo> failureAnalysisInfoList = new ArrayList<>();
        for (Process process:processService.list()){
           failureAnalysisInfoList.add(getAnalysisInfoByPId( process.getID()).getData());
        }
        AjaxMessage<List<FailureAnalysisInfo> > message = new AjaxMessage<>();
        message.setCode("1");
        message.setMessage("");
        message.setData(failureAnalysisInfoList);
        return message;
    }


}
