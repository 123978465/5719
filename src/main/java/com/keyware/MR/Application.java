package com.keyware.MR;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.keyware.MR.mapper")
@SpringBootApplication
@EnableAsync
//@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class Application {

	public static void main(String[] args) {
        System.setProperty("javax.xml.accessExternalStylesheet", "all");
        SpringApplication.run(Application.class, args);
	}
	
    @Bean
    public TomcatServletWebServerFactory tomcatFactory(){
        return new TomcatServletWebServerFactory(){
 
            @Override
            protected void postProcessContext(Context context) {
                ((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
            }
        };
    }
//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); //注意使用哪种数据库
//        return interceptor;
//    }


}
