package cn.zjy.dayong.demo.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/5/28
 * Time:10:17
 *
 * AOP切面测试
 */
@RestController
public class AopService {

    /**
     * 吃饭的方法
     */
    @RequestMapping("/aop/eat")
    public String eat(){
        System.out.println("开始吃饭了!!!");
        return "开始吃饭了!!!";
    }

    /**
     * 看电影的方法
     */
    @RequestMapping("/aop/movie")
    public void movie(){
        System.out.println("开始看电影!!!!");
    }
}
