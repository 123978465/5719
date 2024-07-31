package com.keyware.MR.util;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.keyware.MR.entity.Faultlibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoutingExcelReader implements ReadListener<Faultlibrary> {

    private List<Faultlibrary> users = new ArrayList<>();


    @Override
    public void invoke(Faultlibrary data, AnalysisContext context) {
        users.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("所有数据读取完毕！");
    }

    @Override
    public boolean hasNext(AnalysisContext analysisContext) {
        return false;
    }

    public List<Faultlibrary> getUsers() {
        return users;
    }

}
