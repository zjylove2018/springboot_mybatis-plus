package cn.zjy.dayong.demo.controller;

import cn.zjy.dayong.demo.pojo.Report;
import cn.zjy.dayong.demo.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/19
 * Time:18:19
 *
 * 报表明细Controller
 */
@Controller
@RequestMapping("/report")
public class ReportController {
    /**
     * 日志打印
     */
    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ReportService reportService;

    /**
     * 先启动redis服务
     * 访问路径 localhost:2080  (首页)
     * @param request
     * @return
     */
    @RequestMapping("/findAllReport")
    public String findAllReport(HttpServletRequest request){
        HttpSession session = request.getSession();
        //查询所有的订单
        List<Report> reportList = reportService.findAllReport();
        for (Report report : reportList) {
            report.setSumMoney(report.getOverheadMoney() * report.getOverheadCount());
        }
        session.setAttribute("reportList", reportList);
        return "thymeleaf/dashboard::asynchronous_refresh";
    }

    @RequestMapping("/getDatas")
    @ResponseBody
    public Map<String, Object> getDatas(ModelMap modelMap){

        System.out.println("1111111111111");
        Map<String, Object> map = new HashMap();
        Map<String, Object> datasets = new HashMap<>();

        String [] labels = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Integer [] data = {15339, 21345, 18483, 24003, 23489, 24092, 12034};
        Integer lineTension = 0;
        String backgroundColor = "transparent";
        String borderColor = "#007bff";
        Integer borderWidth = 4;
        String pointBackgroundColor = "#007bff";
        datasets.put("data", data);
        datasets.put("lineTension", lineTension);
        datasets.put("backgroundColor", backgroundColor);
        datasets.put("borderColor", borderColor);
        datasets.put("borderWidth", borderWidth);
        datasets.put("pointBackgroundColor", pointBackgroundColor);
        map.put("labels", labels);
        map.put("datasets", datasets);
        modelMap.addAttribute("data", map);
        return map;
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
