package cn.zjy.dayong.demo.work;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/8
 * Time:9:57
 *
 * 双重检查实例单例
 */
public class SingleTest2 {

    private static Jdk8Test instanle;

    private SingleTest2(){

    }

    public static Jdk8Test getInstanle(){
        if(instanle == null){
            synchronized(SingleTest2.class){
                if(instanle == null){
                    instanle = new Jdk8Test();
                }
            }
        }
        return instanle;
    }
}
