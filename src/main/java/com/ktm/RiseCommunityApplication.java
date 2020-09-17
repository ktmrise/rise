package com.ktm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ktm.mapper")
public class RiseCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiseCommunityApplication.class, args);
        System.out.println("http://localhost:8080/");
    }

}
