package cn.zjy.dayong.demo.work;

import org.junit.Test;

import java.util.*;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/4/9
 * Time:15:29
 *
 * Map集合的4种遍历
 */
public class MapTest {

    @Test
    public void method1(){

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        map.put(5, "e");

        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            System.out.println("key:" + key + ", value:" + map.get(key));
        }
    }

    @Test
    public void method2(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        map.put(5, "e");

        Collection<String> values = map.values();
        for (String value : values) {
            System.out.println("value:" + value);
        }
    }

    @Test
    public void method3(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        map.put(5, "e");

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println("key:" + entry.getKey() + ", value:" + entry.getValue());
        }
    }

    @Test
    public void method4(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        map.put(5, "e");

        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        for (Map.Entry<Integer, String> entry : entrySet) {
            System.out.println("key:" + entry.getKey() + ", value:" + entry.getValue());
        }
    }
}
