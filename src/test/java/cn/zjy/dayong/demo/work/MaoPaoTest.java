package cn.zjy.dayong.demo.work;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/9
 * Time:13:44
 *
 * 冒泡排序测试
 */
public class MaoPaoTest {

    @Test
    public void run(){

        int [] ints = {5,36,4,66,86,3,23,99,34,22,1,56};

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length - 1 - i; j++) {
                if(ints[j] > ints[j + 1]){
                    int temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
        Arrays.sort(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    private static volatile Jdk8Test instanl;
    private MaoPaoTest(){

    }
    public static Jdk8Test getInstanl(){
        if(instanl == null){
            synchronized (Jdk8Test.class){
                if(instanl == null){
                    instanl = new Jdk8Test();
                }
            }
        }
        return instanl;
    }
}
