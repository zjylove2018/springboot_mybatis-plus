package cn.zjy.dayong.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"cn.zjy.dayong.demo.service","cn.zjy.dayong.demo.controller", "cn.zjy.dayong.demo.rabbitmqreceiver","cn.zjy.dayong.demo.rabbitmqserver","cn.zjy.dayong.demo.pojo"})
@MapperScan("cn.zjy.dayong.demo.mapper")
@EnableCaching
@ServletComponentScan
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
