package com.zjh.eduCms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 前台首页显示模块
 */
@SpringBootApplication(scanBasePackages = "com.zjh")
@MapperScan("com.zjh.eduCms.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
