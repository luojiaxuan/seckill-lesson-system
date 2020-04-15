package com.example.two.twodemo;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.actuate.autoconfigure.audit.AuditAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Configuration
//@Import({
//        AopAutoConfiguration.class,
//        DataSourceAutoConfiguration.class
//})
public class TwodemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(TwodemoApplication.class, args);
    }

}
