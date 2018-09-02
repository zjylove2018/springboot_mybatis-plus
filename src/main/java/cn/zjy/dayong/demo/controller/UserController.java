package cn.zjy.dayong.demo.controller;


import cn.zjy.dayong.demo.pojo.User;
import cn.zjy.dayong.demo.service.UserService;
import cn.zjy.dayong.demo.utils.ResponseMessage;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
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

    /**
     * 根据用户id查询用户   Restful 风格
     * 请求路径 : http://localhost:2080/user/getUserById/3
     * @param id
     * @return
     */
	@RequestMapping("/getUserById/{id}")
    @ResponseBody
	public ResponseMessage getUserById(@PathVariable Integer id){
        User user = userService.selectById(id);
        logger.info("获取到的用户为:{}",user);
        return new ResponseMessage().ok().put("获取用户成功!",user);
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
        user.setSex("男");
        userService.insert(user);
        return new ResponseMessage().ok().put("添加用户成功!",user);
    }

    /**
     * 请求路径 : http://localhost:2080/user/selectUserPage/1/3
     * 分页查询用户
     */
    @RequestMapping("/selectUserPage/{current}/{size}")
    @ResponseBody
    public ResponseMessage selectUserPage(@PathVariable Integer current, @PathVariable Integer size){
        List<User> userList = userService.selectPage(new Page<User>(current,size)).getRecords();
        logger.info("获取到的分页用户为:{}",userList );
        return new ResponseMessage().ok().put("分页查询用户成功!",userList);
    }

    /**
     * 请求路径 : http://localhost:2080/user/deleteUser/
     * 删除用户
     */
    @RequestMapping("/deleteUser/{id}")
    @ResponseBody
    public ResponseMessage deleteUser(@PathVariable Integer id){
        User user = userService.selectById(id);
        logger.info("查询到的用户为:{}",user );
        boolean b = userService.deleteById(Integer.valueOf(id).longValue());
        logger.info("是否成功删除用户:{}",b );
        return new ResponseMessage().ok().put("是否成功删除用户!",b);
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
        user.setSex("女");
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
        //new Page<User>(3, 2)  分页显示数据  2代表显示几条数据   3代表要显示的数据是从(3-1)*2 + 1条开始显示的
        List<User> userList = userService.selectPage(new Page<User>(2, 4),
                new EntityWrapper<User>().eq("name", "李李李李")
                        .eq("sex", "男")
                        .between("age", "10", "50")).getRecords();
        logger.info("多条件分页查询到的用户为:{}",userList );
        return new ResponseMessage().ok().put("多条件分页查询到的用户为!",userList);
    }

    /**
     * 请求路径 : http://localhost:2080/user/selectOneUser/
     * mybatis原生查询对象
     */
    @RequestMapping("/selectOneUser/{id}")
    @ResponseBody
    public ResponseMessage selectOneUserById(@PathVariable Integer id){
        User user = userService.selectOneUserById(id);
        logger.info("原生mybatis查询到的用户为:{}",user );
        return new ResponseMessage().ok().put("原生mybatis查询到的用户为!",user);
    }
}
