package cn.zjy.dayong.demo.controller;

import cn.zjy.dayong.demo.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/3/11
 * Time:17:33
 * 测试shiro框架功能
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, String vcod, Boolean remeberMe){
        System.out.println(username);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, remeberMe);
        SecurityUtils.getSubject().login(token);
        return "loginSuccess";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        return "home";
    }
}
