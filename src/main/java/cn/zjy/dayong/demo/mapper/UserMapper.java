package cn.zjy.dayong.demo.mapper;

import cn.zjy.dayong.demo.pojo.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

/**
 * @author zjy
 * @since 2018-08-29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据id查询方法
     * 不使用mybatis-plus框架自带的方法,写sql语句就得这样写了
     * @param longValue
     * @return
     */
    @Results({
            @Result(property="name",column="name"),
            @Result(property="address",column="address" ),
            @Result(property="age",column="age"),
            @Result(property="sex",column="sex" )
    })
    //mybatis原生查询对象
    @Select("SELECT * FROM user where id = #{id}")
    User selectOneUserById(Integer longValue);
    /**
     * 修改操作
     * @Update("UPDATE user SET username=#{username} WHERE id=#{id}")
     *     void updateTest(User user);
     */
    /**
     * 增加操作
     * @Insert("INSERT INTO user(username,age,address,sex," +
     *             "VALUES (#{username},#{age},#{address},#{sex},"  +
     *     void save(User user);
     */

    //根据用户名和密码去查询数据库信息
    User selectUserByUsernameAndPassword(@Param("username")String username,@Param("password")String password);

    //判断用户名是否已被注册
    User selectByUsername(@Param("username")String username);
}