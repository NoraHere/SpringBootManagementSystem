package com.practicen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan //和filter配合
@SpringBootApplication
public class SpringbootMybatisWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisWebManagementApplication.class, args);
    }

}
