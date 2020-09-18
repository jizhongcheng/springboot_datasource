package com.windmill.zyfz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * 开启双数据源
 */
//@SpringBootApplication(exclude= DataSourceAutoConfiguration.class)

//@SpringCloudApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@SpringBootApplication
@Configuration
//开启定时任务
@EnableScheduling       
//@MapperScan(basePackages = "com.windmill.zyfz")//这里是扫描dao接口的包用于识别mybatis
//@EnableTransactionManagement
public class AchiAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AchiAdminApplication.class, args);
    }
}
