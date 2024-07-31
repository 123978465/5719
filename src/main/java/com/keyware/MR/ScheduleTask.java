package com.keyware.MR;

import com.keyware.MR.config.AccessSystem;
import com.keyware.MR.config.AccessSystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author YaoJz
 * @description
 * @date 2024/4/2 11:53
 */
@Component
public class ScheduleTask implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);
    private static String configLocation = null;

    //获取配置文件所在目录
    static {

        //获取当前类所在的目录，即jar包目录
        String property = System.getProperty("user.dir");
        //获取自定义配置文件的路径
        configLocation = property + "/config/access-system.properties";

        LOGGER.info("access-system.properties location:[{}]", configLocation);
    }

    /**
     * 项目启动后，会调用该方法
     */
    @Autowired
    private AccessSystemConfig accessSystemConfig;

    @Override
    public void run(ApplicationArguments args) {
        LOGGER.info("======================================================================");
        LOGGER.info("=== The task for reading the accessSystemConfig has been started ===");
        LOGGER.info("======================================================================");
        readConfig();
    }

    /**
     * 定时加载 accessSystem
     */
//    @Scheduled(cron = "${read.access.system.schedule}")
    public void scheduleTask() {
        readConfig();
    }

    /**
     * 读取配置，将配置加载到程序中
     */
    private void readConfig() {
        LOGGER.info("============= parse accessSystemConfig is start =============");

        long startTime = System.currentTimeMillis();

        //加载文件
//        try{
        try (
//                FileInputStream inputStream = new FileInputStream(configLocation);
             InputStream inputStream = ScheduleTask.class.getClassLoader().getResourceAsStream("config/access-system.properties");
             Reader reader = new InputStreamReader(inputStream);
             BufferedReader br = new BufferedReader(reader)
        ) {
//
//            //存放本次读取出的配置
            Map<String,String> newSystemMap = new HashMap();
//            newSystemList.add(new AccessSystem("000001","=MDAwMDAxQQ=="));
//            newSystemList.add(new AccessSystem("000002","=MDAwMDAyQg=="));
            //将文件映射成properties对象
            Properties properties = new Properties();
            properties.load(br);

//            //解析properties对象,存入newSystemList中
            properties.forEach((k, v) -> {
                //解析秘钥，并保存
//                AccessSystem accessSystem = new AccessSystem(k.toString(), v.toString());
                newSystemMap.put(k.toString(),v.toString());
            });

            //清空原有数据，并保存新获取到的数据（注这里可能存在并发问题，实际使用中需要处理）
            accessSystemConfig.getSystemList().clear();
            accessSystemConfig.setSystemList(newSystemMap);

        } catch (Exception e) {
            LOGGER.warn("read accessSystemConfig is fail, cause by:", e);

        } finally {
            //打印出已加载的系统信息
//            List<String> systemNoList = accessSystemConfig.getSystemList().forEach();
//            LOGGER.info("Loaded systemNoList:{}", systemNoList);

            LOGGER.info("======= parse accessSystemConfig is complete, cost:{}ms ======", System.currentTimeMillis() - startTime);
        }
    }
}
