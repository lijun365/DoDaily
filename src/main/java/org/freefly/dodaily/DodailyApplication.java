package org.freefly.dodaily;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.freefly.dodaily.dao")
public class DodailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DodailyApplication.class, args);
    }

}
