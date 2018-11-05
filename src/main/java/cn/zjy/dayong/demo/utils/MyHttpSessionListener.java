package cn.zjy.dayong.demo.utils;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/5
 * Time:10:55
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

    public static int online = 0;
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("创建session");
        online ++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("销毁session");
    }
}
