package cn.zjy.dayong.demo.service;

import cn.zjy.dayong.demo.pojo.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zjy
 * @since 2018-08-29
 */
public interface UserService extends IService<User> {

    //mybatis原生查询对方方法
    User selectOneUserById(Integer longValue);

    //根据用户名和密码去查询数据库信息
    User selectUserByUsernameAndPassword(String username, String password);

    User selectByUsername(String username);
}
