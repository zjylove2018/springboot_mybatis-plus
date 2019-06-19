package cn.zjy.dayong.demo.work;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IDEA
 * author:zjy
 * Date:2019/5/24
 * Time:14:26
 *
 * Set集合测试
 */
public class SetTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("aaaaa");
        set.add("bbbbb");
        set.add("ccccc");
        set.add("ddddd");
        set.add("eeeee");
        System.out.println(set);
        System.out.println(set.iterator().next());
        System.out.println("-------------------");

        List<String> list = new ArrayList<>(set);
        System.out.println(list.get(0));
        System.out.println(list);
    }
}
