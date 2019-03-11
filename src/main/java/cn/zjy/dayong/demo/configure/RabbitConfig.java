package cn.zjy.dayong.demo.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/8
 * Time:17:02
 * rabbitmq消息队列发送方
 */
@Configuration
public class RabbitConfig {
    //日志打印
    private static final Logger log = LoggerFactory.getLogger(RabbitConfig.class);

    @Bean
    public Queue helloQueue(){
        return new Queue("abc");
    }

    @Bean
    public Queue userLoginQueue(){
        String creat_Queue = "userlogin_queue_test";
        log.info("建立队列Queue:{}",creat_Queue);
        return new Queue(creat_Queue);
    }
}
