package cn.zjy.dayong.demo.rabbitmqreceiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/8
 * Time:17:17
 * rabbitMQ消息队列接收方
 *
 * 1.先把接收方的代码注释掉(防止消息产生了就被消费了)
 * 2.rabbitMQ在本项目中是把用户登录作为消息存放, 先访问http://localhost:2080/freemark/toLogin
 * 3.输入用户名和密码, 然后登录.就会把用户名和密码通过rabbitMQ生产出消息
 * 4.去http://127.0.0.1:15672/ 的Queues查看消息,会有记录
 * 5.把接收方的代码放开注释,再重新启动项目, 则消息会被消费
 */
@Component
public class RabbitMQRecevier {
    //日志打印
    private static final Logger log = LoggerFactory.getLogger(RabbitMQRecevier.class);
    private int count = 5;
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "userlogin_queue_test", durable = "true"),
            exchange = @Exchange(value = "exchange_login", type = "topic", durable = "true"),
            key = "login_key"))
    public void receiveMessage(String message){
        try{
            log.info("RabbitListener消费的队列消息为:{}",message);
        } catch (Exception e){
            log.info("获取异常",e);
        }
        count --;
    }
}
