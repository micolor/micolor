package com.micolor.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.micolor.admin.app.mapper"})
@ComponentScan(basePackages = {"com.micolor.admin", "com.micolor.common"})
@EnableCaching
@EnableSwagger2

public class MicolorAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicolorAdminApplication.class, args);
    }
}
