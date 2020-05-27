package com.chen.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//spring项目启动入口类
@SpringBootApplication      //spring核心注解，主要用于开发spring自动配置x`
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
