package cn.zjy.dayong.demo.configure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/8/28
 * Time:10:49
 */
@Configuration
@MapperScan("cn.zjy.dayong.demo.mapper*")
public class MyBatisPlusConfig {
}
