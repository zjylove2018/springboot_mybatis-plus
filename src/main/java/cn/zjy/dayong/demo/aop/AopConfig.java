package cn.zjy.dayong.demo.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/5/28
 * Time:10:44
 * AOP切面配置类
 */
@Configuration          //配置类
@ComponentScan("cn.zjy.dayong.demo.aop")    //扫描aop的包
@EnableAspectJAutoProxy //开启spring对aop的支持
public class AopConfig {
}
