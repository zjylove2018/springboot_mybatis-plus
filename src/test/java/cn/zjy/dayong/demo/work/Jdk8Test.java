package cn.zjy.dayong.demo.work;

import cn.zjy.dayong.demo.pojo.User;
import cn.zjy.dayong.demo.service.UserService;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/11/19
 * Time:10:08
 * jdk1.8新特性
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Jdk8Test {
    @Autowired
    private UserService userService;

    @Test
    public void run1(){
        List<User> userList = userService.selectPage(new Page<User>(1,8)).getRecords();
        for (User user : userList) {
            System.out.println("查询到的用户集合为:" + user);
        }
        System.out.println("--------------------------");

        //jdk1.8新特性 过滤出来年龄>24的
        List<User> users = userList.stream().filter(k -> new Integer(k.getAge()).compareTo(new Integer(27)) <= 0).collect(Collectors.toList());
        for (User user : users) {
            System.out.println("jdk1.8新特性练习成功.获取到的过滤的用户为:" + user);
        }
    }

    @Test
    public void run2(){
        String [] ids = {"1","2","3","5","6","7"};
        //可以查出来8条数据
        List<User> userList = userService.selectPage(new Page<User>(1,8)).getRecords();
        List<User> users = Arrays.asList(ids).stream()   //234789
                .map(id -> userList.get(new Integer(id)))
                .filter(k -> new Integer(k.getAge()).compareTo(new Integer(26)) < 0).collect(Collectors.toList());
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("-----------------------");
        users.sort(Comparator.comparing(User::getName));
        for (User user : users) {
            System.out.println(user);
        }
    }
}
