package com.keyware.MR.entity;

import lombok.Data;

import java.util.List;

@Data
public class FailureAnalysisInfo {
    private List<Faultlibrary> faultlibraryList;
    private Process process;

    public FailureAnalysisInfo() {
    }

    public FailureAnalysisInfo(List<Faultlibrary> faultlibraryList, Process process) {
        this.faultlibraryList = faultlibraryList;
        this.process = process;
    }

    public List<Faultlibrary> getFaultlibraryList() {
        return faultlibraryList;
    }

    public void setFaultlibraryList(List<Faultlibrary> faultlibraryList) {
        this.faultlibraryList = faultlibraryList;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "FailureAnalysisInfo{" +
                "faultlibraryList=" + faultlibraryList +
                ", process=" + process +
                '}';
    }
}
