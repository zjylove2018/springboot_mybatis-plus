package cn.zjy.dayong.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/9
 * Time:9:55
 */
@RestController
public class SendEmailUtils {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sendName;

    @Value("${spring.mail.jndi-name}")
    private String receiveName;

    @RequestMapping("/sendEmail")
    public ResponseMessage sendEmailMessage(){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sendName);    //邮件发送方
            simpleMailMessage.setTo(receiveName);   //邮件接收方
            simpleMailMessage.setSubject("主题:能收到不");
            simpleMailMessage.setText("就是来坑你的...哈哈哈!!!");
            javaMailSender.send(simpleMailMessage);
            return new ResponseMessage().ok().put("发送邮件成功!", simpleMailMessage);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseMessage().error().put("发送邮件失败!", e);
        }
    }

}
