package org.freefly.dodaily.sugarmark;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("org.freefly.dodaily.sugarmark.mapper")
public class SugarMarkApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SugarMarkApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SugarMarkApplication.class);
    }
}
