package cn.zjy.dayong.demo.utils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/5
 * Time:10:34
 * 拦截器
 */
public class MyInterceptor implements HandlerInterceptor {
    //在请求处理之前进行调用Controller方法调用之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle被调用了");
        Map map = (Map)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        System.out.println(map.get("name"));
        System.out.println(request.getParameter("username"));
        if(map.get("name").equals("zhangsan")){
            return true;
        }else {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("plsese login again");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle被调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion被调用");
    }
}
