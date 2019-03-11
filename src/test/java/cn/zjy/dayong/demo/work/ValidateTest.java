package cn.zjy.dayong.demo.work;

import cn.zjy.dayong.demo.ValidateCheck.Validate;
import org.junit.Test;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/22
 * Time:13:52
 */
public class ValidateTest {
    @Validate(pattern = "^1(3[0-9]|4[0-9]|5[0-35-9]|8[0-9]|7[0-9]|6[0-9]|9[0-9])\\d{8}$")
    private String mobile;

    @Test
    public void run(){
        mobile = "13403520906111";
        try {
            System.out.println(mobile);
            System.out.println("校验通过");
        } catch (Exception e){
            System.out.println("校验没通过");
        }
    }

    @Test
    public void run2(){
        if(true || false){
            if((true && true) || (false && false)){
                System.out.println("没错");
            }else {
                System.out.println("有错");
            }
        }
    }
}
