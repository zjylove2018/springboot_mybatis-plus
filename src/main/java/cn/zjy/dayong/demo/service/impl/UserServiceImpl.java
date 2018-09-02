package cn.zjy.dayong.demo.service.impl;

import cn.zjy.dayong.demo.pojo.User;
import cn.zjy.dayong.demo.mapper.UserMapper;
import cn.zjy.dayong.demo.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zjy
 * @since 2018-08-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User selectOneUserById(Integer longValue) {
        User user = userMapper.selectOneUserById(longValue);
        return user;
    }
}
