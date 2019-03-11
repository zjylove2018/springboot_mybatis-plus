package cn.zjy.dayong.demo.webFulx;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/3/8
 * Time:9:09
 *
 */
public class RoutingConfiguration {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new RoutingConfiguration());
        eventBus.post("hi:boys");
        String s = eventBus.identifier();
        System.out.println(s);
    }
    @Subscribe
    public void sendMessageByEmail(String message){
        System.out.println("邮件发送一条消息:" + message);
    }

    @Subscribe
    public void sendMessageByPhone(String message){
        System.out.println("手机发送一条消息:" + message);
    }
}
