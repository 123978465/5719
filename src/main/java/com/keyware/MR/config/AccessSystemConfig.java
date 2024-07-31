package com.keyware.MR.config;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YaoJz
 * @description
 * @date 2024/4/2 11:52
 */
@Component
public class AccessSystemConfig {

    private static  Map<String,String> systemMap = new HashMap<>();


    public  Map<String,String> getSystemList() {
        return systemMap;
    }
    public static String getValueByKey(String key){
        if (!systemMap.containsKey(key)){return null;}
        return systemMap.get(key);
    }

    public static void setValueByKey(String key,String value){
        if (!systemMap.containsKey(key)){return ;}
         systemMap.put(key,value);
    }
    public void setSystemList( Map<String,String> systemMap) {
        this.systemMap = systemMap;
    }
}
