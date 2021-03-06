package cn.zjy.dayong.demo.controller;

import cn.zjy.dayong.demo.utils.MyHttpSessionListener;
import cn.zjy.dayong.demo.utils.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/5
 * Time:11:02
 * 测试过滤器,拦截器,监听器
 */
@Controller
public class UserTestController {
    private final Logger logger = LoggerFactory.getLogger(UserTestController.class);


    private String message = "hello world";

    @GetMapping("/asd/{name}")
    public String welcome(@PathVariable String name, Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("time",new Date());
        map.put("message",message);
        model.addAttribute("model",map);
        return "welcome";
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseMessage foo(){

        return new ResponseMessage().ok().put("用户登录成功!","");
    }

    @RequestMapping("/online")
    @ResponseBody
    public Object online(){
        return "当前在线人数为:" + MyHttpSessionListener.online / 2 + "人";
    }
}
