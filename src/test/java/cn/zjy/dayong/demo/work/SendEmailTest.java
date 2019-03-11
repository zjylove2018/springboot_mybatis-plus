package cn.zjy.dayong.demo.work;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/7
 * Time:10:32
 * 发送邮件测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("126")
public class SendEmailTest {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sendName;

    @Test
    public void sendEmail(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sendName);    //邮件发送方
        simpleMailMessage.setTo(sendName);   //邮件接收方
        simpleMailMessage.setSubject("主题:能收到不");
        simpleMailMessage.setText("就是来坑你的...哈哈哈!!!");
        javaMailSender.send(simpleMailMessage);
    }

    @Test
    public void run(){

    }
}

