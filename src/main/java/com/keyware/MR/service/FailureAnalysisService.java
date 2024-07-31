package com.keyware.MR.service;

import com.keyware.MR.entity.FailureAnalysisInfo;
import com.keyware.MR.util.AjaxMessage;

import java.util.List;

public interface FailureAnalysisService {
    public AjaxMessage<FailureAnalysisInfo> getAnalysisInfoByPId(Integer processId);
    public String dataGeneration(String max,String min);
    public AjaxMessage<List<FailureAnalysisInfo>> getInfoList();


    }
