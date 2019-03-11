package cn.zjy.dayong.demo.configure;

import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/8
 * Time:17:17
 * rabbitMQ消息队列接收方
 */
@Component
public class RabbitMQRecevier {
    //日志打印
    /*private static final Logger log = LoggerFactory.getLogger(RabbitMQRecevier.class);
    private int count = 5;
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "userlogin_queue_test", durable = "true"),
            exchange = @Exchange(value = "exchange_login", type = "topic", durable = "true"),
            key = "login_key"))
    public void receiveMessage(String message){
        try{
            log.info("RabbitListener获取到的队列消息为:{}",message);
        } catch (Exception e){
            log.info("获取异常",e);
        }
        count --;
    }*/
}
