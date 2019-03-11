package cn.zjy.dayong.demo.controller;

import cn.zjy.dayong.demo.pojo.Resource;
import cn.zjy.dayong.demo.pojo.User;
import cn.zjy.dayong.demo.service.UserService;
import cn.zjy.dayong.demo.utils.JavaWebTokenUtils;
import cn.zjy.dayong.demo.utils.ResponseMessage;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/10/23
 * Time:16:06
 * freemark静态化页面
 */
@Controller
@RequestMapping("/freemark")
public class FreemarkController {

    /**
     * 日志打印
     */
    private final Logger logger = LoggerFactory.getLogger(FreemarkController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Resource resource;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    //访问路径:http://localhost:2080/freemark/index
    @RequestMapping("/index")
    public String index(ModelMap map){
        map.addAttribute("resource",resource);
        return "/index.html";
    }

    //访问路径:http://localhost:2080/freemark/center
    @RequestMapping("/center")
    public String center(ModelMap map){
        List<Map> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Map map1 = new HashMap();
            map1.put("name","kevin_" + i);
            map1.put("age", 20 + i);
            map1.put("phone", "137188888" + i);
            list.add(map1);
        }
        map.put("users",list);
        map.put("title","freemark用户列表");
        return "center";
    }

    /**
     * 去注册页面
     * 访问路径:http://localhost:2080/freemark/toRegister
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    /**
     * 注册功能实现
     * 访问路径:http://localhost:2080/freemark/register
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResponseMessage register(User user, HttpServletRequest request){
        //判断用户名是否可用
        User user2 = userService.selectByUsername(user.getUsername());
        if(user2 != null){
            logger.info("该用户名已被注册:{}",user2);
//            request.setAttribute("message","该用户名已被注册");
            return new ResponseMessage().error().put("该用户名已被注册!","用户名是:" + user.getUsername());
        }
        MessageDigest md5= null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            logger.info("加密前的密码是:{}", user.getPassword());
            //加密后的字符串
            String newstr=base64en.encode(md5.digest(user.getPassword().getBytes("utf-8")));
            logger.info("加密后的密码是:{}", newstr);
            user.setPassword(newstr);
            //保存进数据库
            userService.insert(user);
            //TODO  添加完了用户给用户发送一封邮件,提示用户注册成功

            return new ResponseMessage().ok().put("添加用户成功!",user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseMessage().error().put("添加用户失败!",user);
        }
    }

    /**
     * 去登录页面
     * 访问路径:http://localhost:2080/freemark/toLogin
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(User user){
        return "login";
    }

    /**
     * 登录功能实现
     * 访问路径:http://localhost:2080/freemark/login
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResponseMessage login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //TODO增加验证码
//        String encode = request.getParameter("username");
        User user =userService.selectUserByUsernameAndPassword(username,password);
        String token = "";
        if(user != null){
            Map<String,Object> m = new HashMap<String,Object>();
            m.put("user_id", user.getId());
            token = JavaWebTokenUtils.createJavaWebToken(m);
            logger.info("用户登录成功得到的token:{}", token);
            request.setAttribute("token",token);

            final String key = "user_" + user.getId();
            ValueOperations<String, User> operations = redisTemplate.opsForValue();
            operations.set(key, user, 100, TimeUnit.SECONDS);
            logger.info("用户校验登录成功!加入缓存:{}", user.toString());
            Map<String,Object> map = JavaWebTokenUtils.parserJavaWebToken(token);
            if(map != null){
                Object object = map.get("user_id");
                User user2 = userService.selectOneUserById((Integer)object);
                if(username.equals(user2.getUsername())){
                    logger.info("token校验通过,是用户:{}", user2);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("userSission",user2.getUsername());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("username",user2.getUsername());
                    jsonObject.put("password",user2.getPassword());
                    rabbitTemplate.convertAndSend("exchange_login","login_key",jsonObject.toString());
                    logger.info("rabbitMQ发送的消息为:{}",jsonObject.toString());
                }else {
                    logger.info("token校验失败,可能被篡改过token!");
                    return new ResponseMessage().error().put("token校验失败,可能被篡改过token!",token);
                }
                return new ResponseMessage().ok().put("用户登录成功!",user2);
            }else {
                logger.info("token校验失败,可能被篡改过token!");
                request.setAttribute("loginMessageError","token校验失败,可能被篡改过token!");
                return new ResponseMessage().error().put("token校验失败,可能被篡改过token!",token);
            }
        }else {
            request.setAttribute("loginMessageError","请输入正确的用户名或密码");
            return new ResponseMessage().error().put("loginMessageError","用户名或密码输入错误!");
        }
    }
}
