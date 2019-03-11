package cn.zjy.dayong.demo.work;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/16
 * Time:10:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeRevert {

    @Test
    public void run(){
        Date date = new Date(79200000);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sd.format(date));
    }

    @Test
    public void run2(){
        Date date = new Date(48600000); //1970-01-01 21:30:00
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sd.format(date));
    }

    @Test
    public void run3(){
        Date date = new Date(75549221); //1970-01-02 04:59:09
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sd.format(date));
    }

    @Test
    public void run4(){
        Date date = new Date(48600000);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sd.format(date));
    }
}
