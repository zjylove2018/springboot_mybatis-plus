package cn.zjy.dayong.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/8
 * Time:10:06
 * 定时器  @Scheduled(cron = "0 0/5 * * * ?")  5分钟的倍数时间执行一次
 */
@Component
public class QuartzUtils {

    //日志打印
    private static final Logger logger = LoggerFactory.getLogger(QuartzUtils.class);
    private int count = 1;

    @Scheduled(cron = "0 0/5 * * * ?")
    private void proces(){
        String content="这是第"+(count++)+"封邮件";
        logger.info("springboot整合定时器实现定时邮件发送:{}",content);
    }
}
