package cn.zjy.dayong.demo.work;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/8
 * Time:11:12
 *
 * 饿汉式单例
 */
public class SingleTest4 {

    private static Jdk8Test INSTANLE = new Jdk8Test();

    private SingleTest4(){

    }

    public static Jdk8Test getInstanle(){

        return INSTANLE;
    }
}
