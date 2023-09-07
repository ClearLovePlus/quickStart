package com.quick.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @description:
 * @author: chenhao
 * @date: 2023-7-19 17:32
 */
@SpringBootApplication(scanBasePackages = {"com.quick.**"}, exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.quick.**.mapper")
public class QuickApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuickApplication.class, args);
    }
}
