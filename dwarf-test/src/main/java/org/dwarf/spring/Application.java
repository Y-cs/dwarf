package org.dwarf.spring;

import org.dwarf.spring.ano.EnableRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 17:59
 * @Description:
 **/
@SpringBootApplication
@EnableRecord
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
