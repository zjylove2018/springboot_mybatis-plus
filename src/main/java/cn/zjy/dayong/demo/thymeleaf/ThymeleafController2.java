package cn.zjy.dayong.demo.thymeleaf;

import cn.zjy.dayong.demo.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/16
 * Time:10:00
 *
 * thymeleaf 模板Controller
 */
@Controller
public class ThymeleafController2 {

    /**
     * 日志打印
     */
    private final Logger logger = LoggerFactory.getLogger(ThymeleafController2.class);

    /**
     *
     * 访问链接: http://localhost:2080/thymeleaf/user
     * 点击登录的话得开启redis缓存
     * @param
     * @return
     */
    @RequestMapping("/thymeleaf/user")
    public String index(ModelMap model, HttpServletRequest request){

        User user1 = new User();
        user1.setUsername("周周周周");
        user1.setAge(26);
        user1.setAddress("<font color='green'><b>talk is cheap, show me the code</b></font>");

        User user2 = new User();
        user2.setUsername("王王王");
        user2.setAge(24);
        user2.setAddress("<font color='yellow'><b>talk is cheap, show me the code</b></font>");

        User user3 = new User();
        user3.setUsername("张张张");
        user3.setAge(22);
        user3.setAddress("<font color='red'><b>talk is cheap, show me the code</b></font>");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        HttpSession session = request.getSession();
        model.addAttribute("user1", user1);
        model.addAttribute("userList", userList);
        model.addAttribute("user2", session.getId());
        logger.info("当前在线用户获取到的SessionID为: {}", session.getId());

        return "thymeleaf/ajaxtest::table_refresh";
    }

    @RequestMapping("/ajaxTest")
    public String globalRefresh(ModelMap model) {
        List<Map<String,String>> lists = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("author", "曹雪芹");
        map.put("title", "《红楼梦》");
        map.put("url", "www.baidu.com");
        lists.add(map);

        // 用作对照
        model.addAttribute("refresh", "我不会被刷新");
        model.addAttribute("title", "我的书单");
        model.addAttribute("books", lists);
        return "thymeleaf/ajaxtest";
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("/toOutLogin")
    public String outLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("user2") != null
                && session.getAttribute("user2").equals(session.getId())){
            session.removeAttribute("user2");
        }
        logger.info("退出登录获取到的SessionID为: {}", session.getId());
        return "thymeleaf/outLogin";
    }
        
}
