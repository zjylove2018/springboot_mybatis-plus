package cn.zjy.dayong.demo.controller;


import cn.zjy.dayong.demo.pojo.User;
import cn.zjy.dayong.demo.service.UserService;
import cn.zjy.dayong.demo.utils.QrcodeUtils;
import cn.zjy.dayong.demo.utils.ResponseMessage;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zjy
 * @since 2018-08-29
 */
@Controller
@RequestMapping("/user")
public class UserController {
    /**
     * 日志打印
     */
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
    private UserService userService;

	@Autowired
    private RedisTemplate redisTemplate;

	//解决存入redis里的key没有序列化   \xac\xed\x00\x05t\x00\x06
    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }
    /**
     * 根据用户id查询用户   Restful 风格
     * 请求路径 : http://localhost:2080/user/getUserById/3
     *              连着访问两次路径就可以出现二维码 id:26,37,38有图片
     * @param id
     * @return
     */
	@RequestMapping("/getUserById/{id}")
    @ResponseBody
	public ResponseMessage getUserById(@PathVariable Integer id, HttpServletResponse resp){
        final String key = "user_" + id;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            User user = operations.get(key);
            logger.info("从缓存中获取了用户:{} ", user.toString());
            Map<Object,Object> resultMap = new HashMap<>();
            BitMatrix bm = QrcodeUtils.getQrcode(user.toString(),resp);
            resultMap.put("user",user);
            resultMap.put("Qrcode",bm);
            return new ResponseMessage().ok().put("从redis缓存中获取用户成功!",resultMap);
        }
        User user = userService.selectById(id);
        //key:是redis里的key  user:是存放的对象   60:是缓存对象,默认时间是秒    TimeUnit.SECONDS:?
        operations.set(key, user, 100, TimeUnit.SECONDS);
        logger.info("从数据库中获取到的用户对象,插入缓存:{}", user.toString());
        return new ResponseMessage().ok().put("从数据库中获取用户成功!",user);
    }

    /**
     * 请求路径 : http://localhost:2080/user/insertUser
     * 添加用户
     */
    @RequestMapping("/insertUser")
    @ResponseBody
    public ResponseMessage insertUser(){
        User user = new User();
        user.setName("李李李李");
        user.setAge(27);
        user.setAddress("山西");
        user.setUserGender("男");
        userService.insert(user);
        //TODO  添加完了用户给用户发送一封邮件,提示用户注册成功

        logger.info("要进入rabbitMQ的用户对象为:{}",user);
        return new ResponseMessage().ok().put("添加用户成功!",user);
    }

    /**
     * 请求路径 : http://localhost:2080/user/selectUserPage/1/3
     * 分页查询用户
     */
    @RequestMapping("/selectUserPage/{current}/{size}")
    @ResponseBody
    public ResponseMessage selectUserPage(@PathVariable Integer current, @PathVariable Integer size){

        final String key = "pageUser_" + current + "_" + size;
        ValueOperations<String, List<User>> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            List<User> userList = operations.get(key);
            logger.info("从缓存中获取了用户:{} ", userList);
            return new ResponseMessage().ok().put("从redis缓存中获取用户集合成功!",userList);
        }
        List<User> userList = userService.selectPage(new Page<User>(current,size)).getRecords();

        //key:是redis里的key  user:是存放的对象   60:是缓存对象,默认时间是秒    TimeUnit.SECONDS:?
        operations.set(key, userList, 60, TimeUnit.SECONDS);
        logger.info("从数据库中获取到的用户对象,插入缓存:{}", userList);
        return new ResponseMessage().ok().put("从数据库中获取到分页查询用户成功!",userList);
    }

    /**
     * 请求路径 : http://localhost:2080/user/deleteUser/
     * 删除用户
     */
    @RequestMapping("/deleteUser/{id}")
    @ResponseBody
    public ResponseMessage deleteUser(@PathVariable Integer id){
        final String key = "user_" + id;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        // 缓存存在先把缓存的删除掉再删除数据库的
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
        }
        User user = userService.selectById(id);
        logger.info("从数据库查询到的用户为:{}",user );
        boolean b = userService.deleteById(Integer.valueOf(id).longValue());
        logger.info("是否成功从数据库删除用户:{}",b );
        return new ResponseMessage().ok().put("是否成功从数据库删除用户!",b);
    }

    /**
     * 请求路径 : http://localhost:2080/user/updateUser/
     * 修改用户
     */
    @RequestMapping("/updateUser/{id}")
    @ResponseBody
    public ResponseMessage updateUser(@PathVariable Integer id){
        //根据id查到一个user
        User user = userService.selectById(Integer.valueOf(id).longValue());
        logger.info("修改前的用户为:{}",user );
        user.setUserGender("女");
        user.setAge(22);
        boolean b = userService.updateById(user);
        logger.info("修改后的用户为:{}",user );
        return new ResponseMessage().ok().put("修改后的用户为!",user);
    }

    /**
     * 请求路径 : http://localhost:2080/user/selectUser
     * 多条件分页查询
     */
    @RequestMapping("/selectUser")
    @ResponseBody
    public ResponseMessage selectUser(){
        final String key = "pageUser_";
        ValueOperations<String, List<User>> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            List<User> userList = operations.get(key);
            logger.info("从缓存中获取了用户:{} ", userList);
            return new ResponseMessage().ok().put("从redis缓存中获取用户集合成功!",userList);
        }
        //new Page<User>(3, 2)  分页显示数据  2代表显示几条数据   3代表要显示的数据是从(3-1)*2 + 1条开始显示的
        List<User> userList = userService.selectPage(new Page<User>(2, 4),
                new EntityWrapper<User>().eq("name", "李李李李")
                        .eq("sex", "男")
                        .between("age", "10", "50")).getRecords();
        operations.set(key, userList, 60, TimeUnit.SECONDS);
        logger.info("从数据库中获取到的用户对象,插入缓存:{}", userList);
        return new ResponseMessage().ok().put("从数据库中获取到多条件分页查询用户成功!",userList);
    }

    /**
     * 请求路径 : http://localhost:2080/user/selectOneUser/
     * mybatis原生查询对象
     */
    @RequestMapping("/selectOneUser/{id}")
    @ResponseBody
    public ResponseMessage selectOneUserById(@PathVariable Integer id){
        final String key = "user_" + id;
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            User user = operations.get(key);
            logger.info("原生mybatis从缓存中获取了用户:{} ", user.toString());
            return new ResponseMessage().ok().put("使用原生mybatis从redis缓存中获取用户成功!",user);
        }
        User user = userService.selectOneUserById(id);
        operations.set(key, user, 60, TimeUnit.SECONDS);
        logger.info("原生mybatis查询到的用户为:{}",user );
        return new ResponseMessage().ok().put("原生mybatis从数据库中查询到的用户为!",user);
    }

}
