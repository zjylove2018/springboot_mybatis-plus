package cn.zjy.dayong.demo.work;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/8
 * Time:10:24
 *
 * 单例最终版本
 */
public class SinglelTest3 {

    private static volatile Jdk8Test instal;

    private SinglelTest3(){

    }

    public static Jdk8Test getInstal(){
        if(instal == null){
            synchronized (SinglelTest3.class){
                if(instal == null){
                    instal = new Jdk8Test();
                }
            }
        }
        return instal;
    }

}
