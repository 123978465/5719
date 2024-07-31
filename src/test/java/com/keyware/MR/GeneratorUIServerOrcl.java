package com.keyware.MR;

import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;
import com.github.davidfantasy.mybatisplus.generatorui.mbp.NameConverter;

public class GeneratorUIServerOrcl {

    public static void main(String[] args) {
//        driver-class-name: oracle.jdbc.driver.OracleDriver
//        url: jdbc:oracle:thin:@172.16.36.197:1521/helowin
//        username: ks
//        password: 123456
    	//url: jdbc:oracle:thin:@192.168.121.129:1521/orcl
        GeneratorConfig config = GeneratorConfig.builder().jdbcUrl("jdbc:oracle:thin:@192.168.121.129:1521/orcl")
//        GeneratorConfig config = GeneratorConfig.builder().jdbcUrl("jdbc:oracle:thin:@172.16.36.197:1529/helowin")
                .userName("ks")
                .password("123456")
                .driverClassName("oracle.jdbc.driver.OracleDriver")
                //数据库schema，POSTGRE_SQL,ORACLE,DB2类型的数据库需要指定
//                .schemaName("helowin")
                //如果需要修改各类生成文件的默认命名规则，可自定义一个NameConverter实例，覆盖相应的名称转换方法：
                .nameConverter(new NameConverter() {
                    /**
                     * 自定义Service类文件的名称规则
                     */
                    @Override
                    public String serviceNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Service";
                    }
                    /**
                     * 自定义Controller类文件的名称规则
                     */
                    @Override
                    public String controllerNameConvert(String tableName) {
                        return this.entityNameConvert(tableName) + "Controller";
                    }
                })
                .basePackage("com.keyware.xscan")
                .port(8888)
                .build();
        MybatisPlusToolsApplication.run(config);
    }

}
