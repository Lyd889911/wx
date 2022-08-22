package com.lyd.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 描述:
 *
 * @author liyadong
 * @create 2022-08-22-1:03-周一
 */
@EnableScheduling //启动定时任务
@SpringBootApplication
public class WxApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxApplication.class,args);
    }
}
