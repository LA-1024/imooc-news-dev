package com.imooc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@MapperScan(basePackages = "com.imooc.user.mapper")
@ComponentScan(basePackages = "com.imooc")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
