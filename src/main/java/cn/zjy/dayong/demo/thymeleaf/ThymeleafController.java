package cn.zjy.dayong.demo.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/16
 * Time:10:00
 *
 * thymeleaf 模板Controller
 */
@Controller
public class ThymeleafController {

    @RequestMapping("/shouye")
    public String index(ModelMap map){
        map.addAttribute("name", "王王王");
        return "thymeleaf/index1";
    }

}
