package cn.zjy.dayong.demo.mapper;

import cn.zjy.dayong.demo.pojo.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
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
}