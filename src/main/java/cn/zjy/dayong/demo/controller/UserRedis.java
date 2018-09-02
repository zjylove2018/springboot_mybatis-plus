package cn.zjy.dayong.demo.controller;

import cn.zjy.dayong.demo.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created with IDEA
 * author:zjy
 * Date:2018/8/29
 * Time:9:50
 */
public class UserRedis {
    /**
     * 日志打印
     */
    private final Logger logger = LoggerFactory.getLogger(UserRedis.class);

    /**
     * 接下来就是如何使用注解啦,这一步反而是最简单的.其实只用到了两个注解,@Cacheable和@CacheEvict.
     * 第一个注解代表从缓存中查询指定的key,如果有,从缓存中取,不再执行方法.如果没有则执行方法,
     * 并且将方法的返回值和指定的key关联起来,放入到缓存中.而@CacheEvict则是从缓存中清除指定的key对应的数据.
     * 使用的代码如下:
     * @param id
     * @return
     */
    @Cacheable(value="thisredis", key="#id")
    public User findUser(Integer id) {
        User user = new User();
        user.setName("hlhdidi");
        user.setAddress("123");
        user.setId(id.longValue());
        System.out.println("log4j2坏啦?");
        logger.info("输入user,用户名:{},密码:{}",user.getName(),user.getAddress());
        return user;
    }

    @CacheEvict(value="thisredis", key="#id",condition="#id!=1")
    public void delUser(Integer id) {
        // 删除user
        System.out.println("user删除");
    }
}
