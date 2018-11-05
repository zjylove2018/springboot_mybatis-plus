package cn.zjy.dayong.demo.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/5
 * Time:10:24
 * 过滤器
 */
@WebFilter
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("拦截器进入========拦截器进入========");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(servletRequest.getParameter("name"));
        System.out.println("拦截中========拦截中========");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        if(request.getRequestURI().indexOf("/index") != -1 ||
                request.getRequestURI().indexOf("/asd") != -1 ||
                request.getRequestURI().indexOf("/online") != -1 ||
                request.getRequestURI().indexOf("/login") != -1 ){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            wrapper.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {
        System.out.println("拦截器销毁========拦截器销毁========");
    }
}
